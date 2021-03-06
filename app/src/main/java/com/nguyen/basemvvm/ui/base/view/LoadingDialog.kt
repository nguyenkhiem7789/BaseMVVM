package com.nguyen.basemvvm.ui.base.view

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.nguyen.basemvvm.R

/**
 * Created by apple on 9/15/17.
 */
object Loading {
    private var loadingDialog: LoadingDialog? = null

    fun show(context: Context) {
        if(loadingDialog == null) {
            loadingDialog =
                LoadingDialog(context)
        }
        if(!loadingDialog!!.isShowing) {
            loadingDialog!!.show()
        }
    }

    fun dismiss() {
        if(loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }
}

private class LoadingDialog : Dialog {

    constructor(context: Context) : super(context, R.style.SmoothProgressDialog) {
        setContentView(R.layout.layout_loading)
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.dimAmount = 0.7f
        window!!.attributes = lp

        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }
}