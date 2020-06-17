package com.nguyen.basemvvm.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.nguyen.basemvvm.R

/**
 * Created by apple on 9/9/17.
 */
open class BaseActivity: AppCompatActivity() {

    val navigation: NavigationManager by lazy {
        NavigationManager(this)
    }

    override fun onBackPressed() {
        if(navigation.isRoot()) {
            AlertDialog.showDialog(this, this.getString(R.string.title_exit_app),
                this.getString(R.string.content_exit_app), object: AlertDialog.ButtonClickListener {
                    override fun okListener() {
                        finish()
                    }

                })
        } else {
            super.onBackPressed()
        }
    }
}