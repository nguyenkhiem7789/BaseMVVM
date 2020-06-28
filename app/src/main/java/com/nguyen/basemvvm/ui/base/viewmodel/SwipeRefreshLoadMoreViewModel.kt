package com.nguyen.basemvvm.ui.base.viewmodel
import com.nguyen.basemvvm.ui.base.adapter.LoadMoreAdapter
import com.nguyen.basemvvm.utils.DisposableManager
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by apple on 9/22/17.
 */
abstract class SwipeRefreshLoadMoreViewModel (
    var adapter: LoadMoreAdapter<*>
) {
    private var disposableRefresh: Disposable? = null

    private var disposableLoadMore: Disposable? = null

    var isLoading: BehaviorSubject<Boolean> = BehaviorSubject.create()

    var pageSize: Int = 10

    fun isRequestingInformation(): Boolean {
        return false
    }

    fun refresh() {
        // stop load more
        disposableLoadMore?.dispose()
        adapter.restate()
        //reset current pager to 1
        adapter.resetPage()
        disposableRefresh = refreshData()
        DisposableManager.add(disposableRefresh!!)
    }

    fun loadData() {
        // load more data
        adapter.setLoadMoreData {
            // stop refresh data
            disposableRefresh?.dispose()
            // load more
            disposableLoadMore = loadMoreData(it)
            DisposableManager.add(disposableLoadMore!!)
            adapter.isClockLoadMore = false
        }
    }

    abstract fun refreshData(): Disposable?

    abstract fun loadMoreData(page: Int): Disposable?

}