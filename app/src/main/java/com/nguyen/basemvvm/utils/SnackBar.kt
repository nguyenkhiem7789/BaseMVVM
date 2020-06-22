package com.nguyen.basemvvm.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.androidadvance.topsnackbar.TSnackbar

object SnackBar {
    fun show(view: View, message: String, success: Boolean) {
        val snackbar = TSnackbar.make(view, message, TSnackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        if (success) {
            snackbarView.setBackgroundColor(Color.parseColor("#288F50"))
        } else {
            snackbarView.setBackgroundColor(Color.parseColor("#DC1E20"))
        }
        val textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        snackbar.show()
    }
}