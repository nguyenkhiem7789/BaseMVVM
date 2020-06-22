package com.nguyen.basemvvm.ui.base.view

import androidx.fragment.app.Fragment
import com.nguyen.basemvvm.utils.DisposableManager

public abstract class BaseFragment: Fragment() {

    val navigation: NavigationManager by lazy {
        NavigationManager(activity)
    }

    protected val activity: BaseActivity
        get() = getActivity() as BaseActivity

    override fun onDestroy() {
        DisposableManager.clear()
        super.onDestroy()
    }
}