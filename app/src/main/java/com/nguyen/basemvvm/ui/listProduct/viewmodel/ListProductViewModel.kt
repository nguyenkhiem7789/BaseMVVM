package com.nguyen.basemvvm.ui.listProduct.viewmodel

import com.nguyen.basemvvm.data.remote.request.ListProductRequest
import com.nguyen.basemvvm.data.remote.response.ListProductResponse
import com.nguyen.basemvvm.data.remote.response.Product
import com.nguyen.basemvvm.data.repository.ListProductRepository
import com.nguyen.basemvvm.ui.base.viewmodel.SwipeRefreshLoadMoreViewModel
import com.nguyen.basemvvm.ui.listProduct.adapter.ListProductAdapter
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subscribers.DisposableSubscriber

class ListProductViewModel(
    private val repository: ListProductRepository,
    private val productAdapter: ListProductAdapter
) : SwipeRefreshLoadMoreViewModel(productAdapter) {

    var arrayProduct: BehaviorSubject<ArrayList<Product?>> = BehaviorSubject.create()

    var displayEmptyView: BehaviorSubject<Boolean> = BehaviorSubject.create()

    var isRefreshing: BehaviorSubject<Boolean> = BehaviorSubject.create()

    var errorMsg: PublishSubject<String> = PublishSubject.create()

    var request: ListProductRequest? = null

    override fun loadInitialData(): Disposable? {
        if(request == null) {
            return null
        }
        request!!.page = 1
        return repository.getListProduct(request!!).subscribeWith(LoadInitialSubscriber())
    }

    override fun loadAfterData(page: Int): Disposable? {
        if(request == null) {
            return null
        }
        if(page == 1) {
            isLoading.onNext(true)
        }
        request!!.page = page
        return repository.getListProduct(request!!).subscribeWith(LoadAfterSubscriber())
    }

    inner class LoadInitialSubscriber: DisposableSubscriber<ListProductResponse>() {
        override fun onComplete() {
            isRefreshing.onNext(false)
        }

        override fun onNext(response: ListProductResponse) {
            val products = response.result?.arrayProduct
            if(products != null && products.size > 0) {
                displayEmptyView.onNext(false)
                productAdapter.clear()
                productAdapter.addData(products)
                productAdapter.incrementPage()
            } else {
                displayEmptyView.onNext(true)
            }
        }

        override fun onError(t: Throwable?) {
            productAdapter.restate()
            errorMsg.onNext(t.toString())
            isRefreshing.onNext(false)
        }
    }

    inner class LoadAfterSubscriber: DisposableSubscriber<ListProductResponse>() {
        override fun onComplete() {
            isLoading.onNext(false)
        }

        override fun onNext(response: ListProductResponse) {
            val products = response.result?.arrayProduct
            if(products != null && products.size > 0) {
                productAdapter.restate()
                productAdapter.addData(products)
                productAdapter.incrementPage()
            } else {
                productAdapter.restate()
            }
        }

        override fun onError(t: Throwable?) {
            productAdapter.restate()
            errorMsg.onNext(t.toString())
        }
    }
}
