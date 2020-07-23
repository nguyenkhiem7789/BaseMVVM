package com.nguyen.basemvvm.ui.base.viewmodel

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel {
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun cleared() {
        compositeDisposable.clear()
    }
}