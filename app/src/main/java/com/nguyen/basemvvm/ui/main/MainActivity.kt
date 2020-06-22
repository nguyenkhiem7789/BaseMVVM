package com.nguyen.basemvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nguyen.basemvvm.R
import com.nguyen.basemvvm.ui.authentication.view.LoginFragment
import com.nguyen.basemvvm.ui.base.view.BaseActivity
import com.nguyen.basemvvm.ui.base.view.NavigationManager

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.openFragment(R.id.containerFrame, LoginFragment(), NavigationManager.Type.ADD, null)
    }
}