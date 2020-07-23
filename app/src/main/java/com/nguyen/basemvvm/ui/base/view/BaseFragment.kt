package com.nguyen.basemvvm.ui.base.view

import androidx.fragment.app.Fragment
import com.nguyen.basemvvm.ui.base.viewmodel.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment: Fragment() {

    protected abstract fun getViewModel(): BaseViewModel?

    private val compositeDisposable = CompositeDisposable()

    val navigation: NavigationManager by lazy {
        NavigationManager(activity)
    }

    protected val activity: BaseActivity
        get() = getActivity() as BaseActivity

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        getViewModel()?.cleared()
    }
}