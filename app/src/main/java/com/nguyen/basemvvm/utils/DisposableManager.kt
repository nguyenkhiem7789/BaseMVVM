package com.nguyen.basemvvm.utils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable



/**
 * Created by apple on 9/22/17.
 */
object DisposableManager {
    private var compositeDisposable: CompositeDisposable? = null

    private fun getDisposable(): CompositeDisposable {
        if(compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable!!
    }

    fun add(disposable: Disposable) {
        getDisposable().add(disposable)
    }

    fun clear() {
        if(getDisposable().size() > 0) {
            getDisposable().dispose()
            getDisposable().clear()
        }
    }
}