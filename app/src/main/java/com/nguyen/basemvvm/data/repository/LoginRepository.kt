package com.nguyen.basemvvm.data.repository

import com.nguyen.basemvvm.data.remote.network.ApiService
import com.nguyen.basemvvm.data.remote.request.LoginRequest
import com.nguyen.basemvvm.data.remote.response.LoginResponse
import com.nguyen.basemvvm.utils.Constant
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginRepository(val apiService: ApiService) {

    private var isRequestingLogin: Boolean = false

    fun login(request: LoginRequest
    ): Flowable<LoginResponse> {
        return apiService.login(request.username, request.password, Constant
            .CLIENT_ID, Constant.CLIENT_SECRET, Constant.GRANT_PASS_TYPE)
            .doOnSubscribe {isRequestingLogin = true}
            .doOnTerminate {isRequestingLogin = false}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable(BackpressureStrategy.BUFFER)
    }
}