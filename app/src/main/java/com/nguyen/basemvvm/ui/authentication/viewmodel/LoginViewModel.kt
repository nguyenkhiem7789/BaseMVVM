package com.nguyen.basemvvm.ui.authentication.viewmodel

import android.text.TextUtils
import com.nguyen.basemvvm.data.remote.request.LoginRequest
import com.nguyen.basemvvm.data.remote.response.LoginResponse
import com.nguyen.basemvvm.data.repository.LoginRepository
import com.nguyen.basemvvm.utils.DisposableManager
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.ReplaySubject
import io.reactivex.subscribers.DisposableSubscriber

class LoginViewModel(
    private val repository: LoginRepository
) {
    var isLoading: BehaviorSubject<Boolean> = BehaviorSubject.create()
    var errorMsg: BehaviorSubject<String> = BehaviorSubject.create()
    var loginResponse: BehaviorSubject<LoginResponse> = BehaviorSubject.create()

    fun loginValidate(loginRequest: LoginRequest): Boolean{
        if(TextUtils.isEmpty(loginRequest.username)) {
            errorMsg.onNext("The name's empty!")
            return false
        }
        if(TextUtils.isEmpty(loginRequest.password)) {
            errorMsg.onNext("The password's empty!")
            return false
        }
        return true
    }

    fun login(loginRequest: LoginRequest) {
        if(!loginValidate(loginRequest)) {
            return
        }
        isLoading.onNext(true)
        DisposableManager.add(repository.login(loginRequest).subscribeWith(LoginSubscriber()))
    }

    inner class LoginSubscriber: DisposableSubscriber<LoginResponse>() {
        override fun onComplete() {
            isLoading.onNext(false)
        }

        override fun onNext(response: LoginResponse) {
            loginResponse.onNext(response)
        }

        override fun onError(t: Throwable?) {
            errorMsg.onNext(t.toString())
        }
    }
}