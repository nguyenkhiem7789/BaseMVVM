package com.nguyen.basemvvm.data.repository

import com.nguyen.basemvvm.data.remote.network.ApiService
import com.nguyen.basemvvm.data.remote.request.ListProductRequest
import com.nguyen.basemvvm.data.remote.response.ListProductResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListProductRepository(private val apiService: ApiService) {

    private var isRequesting: Boolean = false

    fun getListProduct(request: ListProductRequest
    ): Flowable<ListProductResponse> {
        return apiService.getListProductAsync(request.channel, request.visitorId,
            request.query, request.terminal, request.page, request.limit)
            .doOnSubscribe {isRequesting = true}
            .doOnTerminate {isRequesting = false}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable(BackpressureStrategy.BUFFER)
    }
}