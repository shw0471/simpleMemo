package com.shinhw.simplememo.presentation.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.shinhw.simplememo.R
import com.shinhw.simplememo.databinding.DialogMemoItemMenuBinding

class MemoItemMenuDialog(
    private val context: Context,
    private val onMemoItemMenuDialogButtonListener: OnMemoItemMenuDialogButtonListener
) {

    interface OnMemoItemMenuDialogButtonListener {
        fun onEditClick()
        fun onDeleteClick()
        fun onCancelClick()
    }

    fun callDialog() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding: DialogMemoItemMenuBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_memo_item_menu,
            null,
            false
        )
        dialog.setContentView(binding.root)

        binding.btnEditDialog.setOnClickListener {
            onMemoItemMenuDialogButtonListener.onEditClick()
            dialog.dismiss()
        }

        binding.btnDeleteDialog.setOnClickListener {
            onMemoItemMenuDialogButtonListener.onDeleteClick()
            dialog.dismiss()
        }

        binding.btnCancelMemoItemMenuDialog.setOnClickListener {
            onMemoItemMenuDialogButtonListener.onCancelClick()
            dialog.dismiss()
        }

        dialog.show()
    }
}