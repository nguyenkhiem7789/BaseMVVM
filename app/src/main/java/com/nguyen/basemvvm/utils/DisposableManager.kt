package com.nguyen.basemvvm.utils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable



/**
 * Created by apple on 9/22/17.
 */
object DisposableManager {
    var compositeDisposable: CompositeDisposable? = null

    fun getDisposable(): CompositeDisposable {
        if(compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable!!
    }

    fun add(disposable: Disposable) {
        getDisposable().add(disposable)
    }

    fun dispose() {
        if(getDisposable().size() > 0) {
            getDisposable().dispose()
        }
    }

    fun clear() {
        if(getDisposable().size() > 0)
            getDisposable().clear()
    }
}