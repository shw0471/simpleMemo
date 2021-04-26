package com.shinhw.simplememo.presentation.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.shinhw.simplememo.R
import com.shinhw.simplememo.databinding.DialogDefaultBinding

class DefaultDialog(
    private val context: Context,
    private val title: String,
    private val detail: String,
    private val onDefaultDialogButtonListener: OnDefaultDialogButtonListener
) {

    interface OnDefaultDialogButtonListener {
        fun onOkClick()
        fun onNoClick()
    }

    fun callDialog() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding: DialogDefaultBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_default,
            null,
            false
        )
        dialog.setContentView(binding.root)

        binding.tvTitleDefaultDialog.text = title
        binding.tvDetailDefaultDialog.text = detail

        binding.btnOkDefaultDialog.setOnClickListener {
            onDefaultDialogButtonListener.onOkClick()
            dialog.dismiss()
        }

        binding.btnNoDefaultDialog.setOnClickListener {
            onDefaultDialogButtonListener.onNoClick()
            dialog.dismiss()
        }

        dialog.show()
    }
}