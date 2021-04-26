package com.shinhw.simplememo.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.shinhw.domain.entity.Memo
import com.shinhw.simplememo.R
import com.shinhw.simplememo.databinding.ActivityMainBinding
import com.shinhw.simplememo.presentation.adapter.MemoListAdapter
import com.shinhw.simplememo.presentation.ui.dialog.DefaultDialog
import com.shinhw.simplememo.presentation.ui.dialog.MemoItemMenuDialog
import com.shinhw.simplememo.presentation.viewmodel.MemoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val memoViewModel: MemoViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var memoListAdapter: MemoListAdapter
    private lateinit var curMemo: Memo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        init()
    }

    override fun onRestart() {
        super.onRestart()

        binding.drawer.closeDrawers()
        memoViewModel.getMemoList()
    }

    private fun init() {

        memoListAdapter = MemoListAdapter(object : MemoListAdapter.OnItemClickListener {

            override fun onItemClick(v: View, position: Int) {
                curMemo = memoListAdapter.getMemoList()[position]
                binding.tvDetailName.text = memoListAdapter.getMemoList()[position].name
                binding.tvDetailMemo.text = memoListAdapter.getMemoList()[position].memo
                binding.drawer.openDrawer(GravityCompat.END)
            }

        }, object : MemoListAdapter.OnItemLongClickListener {

            override fun onItemLongClick(v: View, position: Int) {
                curMemo = memoListAdapter.getMemoList()[position]
                openMemoItemMenuDialog()
            }

        })

        binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        binding.btnAddMemo.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            intent.putExtra("isEditMode", false)
            startActivity(intent)
        }

        binding.btnBackMain.setOnClickListener {
            binding.drawer.closeDrawers()
        }

        binding.btnDetailMenu.setOnClickListener {
            openMemoItemMenuDialog()
        }

        binding.rvMemo.adapter = memoListAdapter

        memoViewModel.memoList.observe(this, {
            if (it != null) {
                memoListAdapter.setMemoList(it)
            }
        })

        memoViewModel.messageEvent.observe(this, {
            makeToast(it)
        })

        memoViewModel.getMemoList()
    }

    private fun openMemoItemMenuDialog() {
        MemoItemMenuDialog(
            this, object : MemoItemMenuDialog.OnMemoItemMenuDialogButtonListener {

                override fun onEditClick() {

                    DefaultDialog(
                        this@MainActivity,
                        "Edit",
                        "Will you edit it?",
                        object : DefaultDialog.OnDefaultDialogButtonListener {

                            override fun onOkClick() {
                                val intent = Intent(this@MainActivity, EditorActivity::class.java)
                                intent.putExtra("isEditMode", true)
                                intent.putExtra("id", curMemo.id)
                                intent.putExtra("name", curMemo.name)
                                intent.putExtra("memo", curMemo.memo)
                                startActivity(intent)
                            }

                            override fun onNoClick() {
                                makeToast("Canceled")
                            }
                        }
                    ).callDialog()
                }

                override fun onDeleteClick() {

                    DefaultDialog(
                        this@MainActivity,
                        "Delete",
                        "Will you delete it?",
                        object : DefaultDialog.OnDefaultDialogButtonListener {

                            override fun onOkClick() {
                                memoViewModel.deleteMemo(curMemo)
                                memoViewModel.getMemoList()
                                binding.drawer.closeDrawers()
                            }

                            override fun onNoClick() {
                                makeToast("Canceled")
                            }
                        }
                    ).callDialog()
                }

                override fun onCancelClick() {
                    makeToast("Canceled")
                }

            }
        ).callDialog()
    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}