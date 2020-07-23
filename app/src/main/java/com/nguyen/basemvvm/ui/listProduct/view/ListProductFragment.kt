package com.nguyen.basemvvm.ui.listProduct.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.nguyen.basemvvm.BaseApplication
import com.nguyen.basemvvm.R
import com.nguyen.basemvvm.data.remote.request.ListProductRequest
import com.nguyen.basemvvm.data.remote.response.Product
import com.nguyen.basemvvm.ui.base.view.BaseFragment
import com.nguyen.basemvvm.ui.base.view.Loading
import com.nguyen.basemvvm.ui.base.viewmodel.BaseViewModel
import com.nguyen.basemvvm.ui.listProduct.adapter.ListProductAdapter
import com.nguyen.basemvvm.ui.listProduct.di.ListProductModule
import com.nguyen.basemvvm.ui.listProduct.viewmodel.ListProductViewModel
import com.nguyen.basemvvm.utils.DisposableManager
import com.nguyen.basemvvm.utils.SnackBar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list_product.*
import kotlinx.android.synthetic.main.fragment_list_product.view.*
import kotlinx.android.synthetic.main.fragment_list_product.view.swipeRefreshLayout
import javax.inject.Inject

class ListProductFragment : BaseFragment() {

    @Inject
    lateinit var arrayProduct: ArrayList<Product?>

    @Inject
    lateinit var adapter:ListProductAdapter

    @Inject
    lateinit var viewModel: ListProductViewModel

    private val PAGE_SIZE = 10

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_list_product, container, false)
        setupComponent()
        initView(view)
        getListProduct("")
        setupBinding()
        return view
    }

    private fun initView(view: View) {
        view.productRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        view.productRecyclerView.layoutManager = LinearLayoutManager(activity)
        view.productRecyclerView.adapter = adapter
        viewModel.initLoadAfter(view.productRecyclerView)
        view.swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            //refresh data
            getListProduct("")
        })
    }

    // request api get data list product
    private fun getListProduct(query: String) {
        val request = ListProductRequest(
            "pv_online",
            "",
            query,
            "CP01",
            0,
            PAGE_SIZE
        )
        viewModel.request = request
        //load data
        viewModel.loadInitial()
    }

    private fun setupBinding() {
        DisposableManager.add(viewModel
            .isLoading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if(it) {
                    //start loading
                    Loading.show(activity)
                } else {
                    //stop loading
                    Loading.dismiss()
                }
            })

        DisposableManager.add(viewModel
            .isRefreshing
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                swipeRefreshLayout.isRefreshing = false
            })

        DisposableManager.add(viewModel
            .errorMsg
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                SnackBar.show(view!!, it, false)
            })

        addDisposable(
            viewModel
                .displayEmptyView
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(it) {
                        swipeRefreshLayout.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE
                    } else {
                        swipeRefreshLayout.visibility = View.VISIBLE
                        emptyLayout.visibility = View.GONE
                    }
                }
        )
    }

    private fun setupComponent() {
        context?.let {
            BaseApplication.get(it)
                .appComponent
                .plus(ListProductModule())
                .inject(this)
        }
    }

}