package com.nguyen.basemvvm.ui.base.viewmodel
import androidx.annotation.CallSuper
import com.nguyen.basemvvm.utils.Constant
import com.nguyen.basemvvm.utils.Constant.Companion.REQUEST_FAILED
import com.nguyen.basemvvm.utils.Constant.Companion.REQUEST_NONE
import com.nguyen.basemvvm.utils.Constant.Companion.REQUEST_RUNNING
import com.nguyen.basemvvm.utils.Constant.Companion.REQUEST_SUCCEEDED
import io.reactivex.observers.DisposableMaybeObserver

/**
 * Created by apple on 9/11/17.
 */
abstract class NetworkViewModel {

    abstract fun isRequestingInformation(): Boolean

    protected var lastError: Throwable? = null

    @Constant.Companion.RequestState
    private var requestState: Long = 0L

    init {
        this.requestState = REQUEST_NONE
    }

    @Constant.Companion.RequestState
    fun getRequestState(): Long {
        if(isRequestingInformation()) {
            return REQUEST_RUNNING
        }
        return requestState
    }

    open inner class MaybeNetworkObserver<T>: DisposableMaybeObserver<T>() {

        @CallSuper
        override fun onSuccess(t: T) {
            requestState = REQUEST_SUCCEEDED
        }

        @CallSuper
        override fun onComplete() {
        }

        @CallSuper
        override fun onError(e: Throwable) {
            lastError = e
            requestState = REQUEST_FAILED
        }

    }

    fun isRequestRunning(): Boolean {
        @Constant.Companion.RequestState
        var requestState = getRequestState()
        return requestState == Constant.REQUEST_RUNNING
    }

}