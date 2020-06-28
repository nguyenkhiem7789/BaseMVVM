package com.nguyen.basemvvm.data.model

import io.reactivex.subscribers.DisposableSubscriber

class DataSubscriber<T> : DisposableSubscriber<T>() {

    override fun onComplete() {
    }

    override fun onNext(t: T) {
    }

    override fun onError(t: Throwable?) {
    }

}