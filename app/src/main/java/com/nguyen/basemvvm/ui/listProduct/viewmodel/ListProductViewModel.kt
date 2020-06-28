package com.nguyen.basemvvm.ui.listProduct.viewmodel

import android.util.Log
import com.nguyen.basemvvm.data.remote.request.ListProductRequest
import com.nguyen.basemvvm.data.remote.response.ListProductResponse
import com.nguyen.basemvvm.data.remote.response.Product
import com.nguyen.basemvvm.data.repository.ListProductRepository
import com.nguyen.basemvvm.ui.listProduct.adapter.ListProductAdapter
import com.nguyen.basemvvm.ui.base.viewmodel.SwipeRefreshLoadMoreViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subscribers.DisposableSubscriber

class ListProductViewModel(
    private val repository: ListProductRepository,
    private val productAdapter: ListProductAdapter
) : SwipeRefreshLoadMoreViewModel(productAdapter) {

    var arrayProduct: BehaviorSubject<ArrayList<Product?>> = BehaviorSubject.create()

    var isRefreshing: BehaviorSubject<Boolean> = BehaviorSubject.create()

    var errorMsg: PublishSubject<String> = PublishSubject.create()

    var request: ListProductRequest? = null

    override fun refreshData(): Disposable? {
        if(request == null) {
            return null
        }
        request!!.page == 1
        return repository.getListProduct(request!!).subscribeWith(RefreshProductSubscriber())
    }

    override fun loadMoreData(page: Int): Disposable? {
        if(request == null) {
            return null
        }
        if(page == 1) {
            isLoading.onNext(true)
        }
        request!!.page = page
        return repository.getListProduct(request!!).subscribeWith(LoadMoreProductSubscriber())
    }

    inner class RefreshProductSubscriber: DisposableSubscriber<ListProductResponse>() {
        override fun onComplete() {
            isRefreshing.onNext(false)
        }

        override fun onNext(response: ListProductResponse) {
            val products = response.result?.arrayProduct
            if(products != null) {
                productAdapter.clear()
                if(products.size == 0) {
                    productAdapter.finishLoadMore()
                } else {
                    productAdapter.addData(products)
                    productAdapter.incrementPage()
                }
            }
        }

        override fun onError(t: Throwable?) {
            productAdapter.restate()
            errorMsg.onNext(t.toString())
        }
    }

    inner class LoadMoreProductSubscriber: DisposableSubscriber<ListProductResponse>() {
        override fun onComplete() {
            isLoading.onNext(false)
        }

        override fun onNext(response: ListProductResponse) {
            val products = response.result?.arrayProduct
            if(products != null) {
                productAdapter.restate()
                if(products.size == 0) {
                    productAdapter.finishLoadMore()
                } else {
                    arrayProduct.onNext(products)
                    productAdapter.addData(products)
                    productAdapter.incrementPage()
                }
            }
        }

        override fun onError(t: Throwable?) {
            productAdapter.restate()
            errorMsg.onNext(t.toString())
        }
    }
}
