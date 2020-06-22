package com.nguyen.basemvvm.ui.base.viewmodel
import com.nguyen.basemvvm.ui.base.adapter.LoadMoreAdapter
import com.nguyen.basemvvm.ui.base.view.BaseActivity
import com.nguyen.basemvvm.ui.base.view.Loading
import com.nguyen.basemvvm.utils.DisposableManager
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by apple on 9/22/17.
 */
abstract class SwipeRefreshLoadMoreViewModel (
    var baseActivity: BaseActivity,
    var adapter: LoadMoreAdapter<*>
) {

    var disposableRequest: Disposable? = null

    var disposableLoadMore: Disposable? = null

    var pageSize: Int = 10

    fun isRequestingInformation(): Boolean {
        return false
    }

    fun refreshListener() {
        // stop load more
        DisposableManager.clear()
        adapter.restate()
        //reset current pager to 1
        adapter.resetPage()
        disposableRequest = refreshData()
        DisposableManager.add(disposableRequest!!)
    }

    fun loadData() {
        Loading.show(baseActivity)
        // load more data
        adapter.setLoadMoreData {
            // stop refresh data
            DisposableManager.clear()
            // load more
            disposableLoadMore = loadMoreData(it)
            DisposableManager.add(disposableLoadMore!!)
        }
    }

    inner open class LoadMoreSubscriber<T>: DisposableSubscriber<T>() {
        override fun onComplete() {
        }

        override fun onNext(t: T) {
            Loading.dismiss()
            // restate load more
            adapter.restate()
        }

        override fun onError(t: Throwable?) {
            Loading.dismiss()
            //restate load more
            adapter.restate()
        }

    }

    inner open class RefreshSubscriber<T>: DisposableSubscriber<T>() {
        override fun onComplete() {

        }

        override fun onNext(response: T) {
            Loading.dismiss()
            if (response == null)
                return
            adapter.clearAdapter()
        }

        override fun onError(t: Throwable?) {
            Loading.dismiss()
        }

    }

    abstract fun refreshData(): Disposable

    abstract fun loadMoreData(page: Int): Disposable

    fun onDestroyView() {
        DisposableManager.clear()
    }

}