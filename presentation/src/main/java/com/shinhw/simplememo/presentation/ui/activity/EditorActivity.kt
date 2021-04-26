package com.shinhw.simplememo.presentation.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.shinhw.domain.entity.Memo
import com.shinhw.simplememo.R
import com.shinhw.simplememo.databinding.ActivityEditorBinding
import com.shinhw.simplememo.presentation.ui.dialog.DefaultDialog
import com.shinhw.simplememo.presentation.viewmodel.MemoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class EditorActivity : AppCompatActivity() {

    private val memoViewModel: MemoViewModel by viewModel()

    private var isEditMode = false
    private lateinit var binding: ActivityEditorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editor)
        binding.lifecycleOwner = this

        init()
    }

    private fun init() {
        isEditMode = intent.getBooleanExtra("isEditMode", false)
        binding.etMemoName.setText(intent.getStringExtra("name"))
        binding.etMemoDetail.setText(intent.getStringExtra("memo"))

        binding.btnBackEditor.setOnClickListener {
            save()
        }
    }

    override fun onBackPressed() {
        save()
    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun getMemoId() =
        intent.getIntExtra("id", 0)

    private fun getMemoName() =
        intent.getStringExtra("name")

    private fun getMemoDetail() =
        intent.getStringExtra("memo")

    private fun getCurMemoName() =
        binding.etMemoName.text.toString()

    private fun getCurMemoDetail() =
        binding.etMemoDetail.text.toString()

    private fun getDate(): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
        return dateFormat.format(Date())
    }

    private fun save() {
        if (isEditMode) {

            if (getMemoName().equals(getCurMemoName()) && getMemoDetail().equals(getCurMemoDetail())) {
                finish()
                return
            }

            if (getCurMemoName().isEmpty() && getCurMemoDetail().isEmpty()) {
                DefaultDialog(
                    this,
                    "Delete",
                    "Will you delete it?",
                    object : DefaultDialog.OnDefaultDialogButtonListener {
                        override fun onOkClick() {
                            memoViewModel.deleteMemo(
                                Memo(
                                    getMemoId(),
                                    getCurMemoName(),
                                    getCurMemoDetail(),
                                    getDate()
                                )
                            )
                            finish()
                        }

                        override fun onNoClick() {
                            makeToast("Canceled")
                        }
                    }
                ).callDialog()
                return
            }

            if (getCurMemoName().isEmpty()) {
                makeToast("Please input memo name")
                return
            }

            if (getCurMemoDetail().isEmpty()) {
                makeToast("Please input memo")
                return
            }

            DefaultDialog(
                this,
                "Save",
                "Will you save it?",
                object : DefaultDialog.OnDefaultDialogButtonListener {

                    override fun onOkClick() {
                        memoViewModel.editMemo(
                            Memo(
                                getMemoId(),
                                getCurMemoName(),
                                getCurMemoDetail(),
                                getDate()
                            )
                        )
                        finish()
                    }

                    override fun onNoClick() {
                        makeToast("Canceled")
                    }
                }
            ).callDialog()

        } else {

            if (getCurMemoName().isEmpty() && getCurMemoDetail().isEmpty()) {
                finish()
                return
            }

            if (getCurMemoName().isEmpty()) {
                makeToast("Please input memo name")
                return
            }

            if (getCurMemoDetail().isEmpty()) {
                makeToast("Please input memo")
                return
            }

            DefaultDialog(
                this,
                "Save",
                "Will you save it?",
                object : DefaultDialog.OnDefaultDialogButtonListener {
                    override fun onOkClick() {
                        memoViewModel.addMemo(
                            Memo(
                                getMemoId(),
                                getCurMemoName(),
                                getCurMemoDetail(),
                                getDate()
                            )
                        )
                        finish()
                    }

                    override fun onNoClick() {
                        makeToast("Canceled")
                    }

                }
            ).callDialog()
        }
    }
}