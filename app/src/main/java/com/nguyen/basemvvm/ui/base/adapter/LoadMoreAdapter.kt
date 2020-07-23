package com.nguyen.basemvvm.ui.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.basemvvm.R
import com.nguyen.basemvvm.ui.base.view.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.layout_loading.view.*

/**
 * Created by apple on 9/17/17.
 */
abstract class LoadMoreAdapter<T>(private var arrayData: ArrayList<T?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_LOAD_MORE = 100
    }

    abstract fun getViewTypeItem(position: Int): Int

    abstract fun onCreateViewHolderItem(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindViewHolderItem(holder: RecyclerView.ViewHolder, position: Int)

    private var scrollListener: EndlessRecyclerOnScrollListener? = null

    var parentView: RecyclerView? = null

    var isClockLoadMore = false

    fun setLoadMoreData(loadMoreListener: (currentPage: Int) -> Unit) {
        if(parentView == null) {
            return
        }
        scrollListener = object: EndlessRecyclerOnScrollListener(parentView?.layoutManager as LinearLayoutManager){
            override fun onLoadMore(currentPage: Int) {
                if(!isClockLoadMore) {
                    isClockLoadMore = true
                    loadMore()
                    loadMoreListener(currentPage)
                }
            }
        }
        parentView?.addOnScrollListener(scrollListener!!)
    }

    private fun loadMore() {
        arrayData.add(null)
        parentView?.post(Runnable {  notifyItemInserted(arrayData.size - 1) })
    }

    override fun getItemViewType(position: Int): Int {
        if(arrayData.count() <= 0) {
            return 0
        }
        if(arrayData[position] == null) {
            return TYPE_LOAD_MORE
        } else {
            return getViewTypeItem(position)
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is LoadMoreViewHoler) {
            holder.itemView.loadMoreView.isIndeterminate = true
        } else {
            onBindViewHolderItem(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        viewHolder = when (viewType) {
            TYPE_LOAD_MORE -> {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_loadmore, parent, false)
                LoadMoreViewHoler(view)
            }
            else -> {
                onCreateViewHolderItem(parent, viewType)
            }
        }
        return viewHolder
    }

    fun restate() {
        scrollListener?.restate()
        if(arrayData.size > 0 && arrayData[arrayData.size - 1] == null) {
            arrayData.remove(arrayData[arrayData.size - 1])
            notifyItemRemoved(arrayData.size)
        }
    }

    fun finishLoadMore() {
        scrollListener?.finishLoadMore()
    }

    fun incrementPage() {
        scrollListener?.let {
            it.currentPage++
        }
    }

    fun resetPage() {
        scrollListener?.currentPage = 1
    }

    fun addData(arrayMoreData: ArrayList<T?>) {
        for(i in 0 until arrayMoreData.size) {
            arrayData.add(arrayMoreData[i])
            notifyItemInserted(arrayData.size - 1)
        }
    }

    fun clear() {
        arrayData.clear()
        notifyDataSetChanged()
    }

    class LoadMoreViewHoler(itemView: View): RecyclerView.ViewHolder(itemView)
}