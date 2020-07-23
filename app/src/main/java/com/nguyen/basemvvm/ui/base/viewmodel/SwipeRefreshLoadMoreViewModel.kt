package com.nguyen.basemvvm.ui.base.viewmodel

import androidx.recyclerview.widget.RecyclerView
import com.nguyen.basemvvm.ui.base.adapter.LoadMoreAdapter
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by apple on 9/22/17.
 */
abstract class SwipeRefreshLoadMoreViewModel (
    var adapter: LoadMoreAdapter<*>
) : BaseViewModel() {
    private var disposableRefresh: Disposable? = null

    private var disposableLoadMore: Disposable? = null

    var isLoading: BehaviorSubject<Boolean> = BehaviorSubject.create()

    var pageSize: Int = 10

    fun isRequestingInformation(): Boolean {
        return false
    }

    fun initLoadAfter(recyclerView: RecyclerView) {
        adapter.parentView = recyclerView
        adapter.setLoadMoreData {
            // stop refresh data
            disposableRefresh?.dispose()
            // load more
            disposableLoadMore = loadAfterData(it)
            addDisposable(disposableLoadMore!!)
            adapter.isClockLoadMore = false
        }
    }

    fun loadInitial() {
        // stop load more
        disposableLoadMore?.dispose()
        adapter.restate()
        //reset current pager to 1
        adapter.resetPage()
        disposableRefresh = loadInitialData()
        addDisposable(disposableRefresh!!)
    }

    abstract fun loadInitialData(): Disposable?

    abstract fun loadAfterData(page: Int): Disposable?

}