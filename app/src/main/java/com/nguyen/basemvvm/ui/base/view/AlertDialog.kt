package com.nguyen.basemvvm.ui.base.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Window
import com.nguyen.basemvvm.R
import kotlinx.android.synthetic.main.layout_dialog.view.*

object AlertDialog {

    fun showDialog(context: Context, title: String, content: String, listener: ButtonClickListener) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(view)
        if (!TextUtils.isEmpty(title))
            view.titleText.text = title
        if (!TextUtils.isEmpty(content))
            view.contentText.text = content
        view.okButton.setOnClickListener {
            dialog.dismiss()
            listener.okListener()
        }
        view.cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    interface ButtonClickListener {
        fun okListener()
    }

}