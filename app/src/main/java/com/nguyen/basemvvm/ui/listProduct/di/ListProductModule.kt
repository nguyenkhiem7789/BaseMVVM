package com.nguyen.basemvvm.ui.listProduct.di

import com.nguyen.basemvvm.data.remote.network.ApiService
import com.nguyen.basemvvm.data.remote.response.Product
import com.nguyen.basemvvm.data.repository.ListProductRepository
import com.nguyen.basemvvm.ui.listProduct.adapter.ListProductAdapter
import com.nguyen.basemvvm.ui.base.di.scope.FragmentScope
import com.nguyen.basemvvm.ui.listProduct.viewmodel.ListProductViewModel
import dagger.Module
import dagger.Provides

@Module
class ListProductModule {

    @Provides
    @FragmentScope
    fun providerListProduct() : ArrayList<Product?> {
        return ArrayList()
    }

    @Provides
    @FragmentScope
    fun provideListProductAdapter(arrayProduct: ArrayList<Product?>) : ListProductAdapter {
        return ListProductAdapter(arrayProduct)
    }

    @Provides
    @FragmentScope
    fun provideListProductRepository(apiService: ApiService) : ListProductRepository {
        return ListProductRepository(apiService)
    }

    @Provides
    @FragmentScope
    fun provideListProductViewModel(repository: ListProductRepository, adapter: ListProductAdapter): ListProductViewModel {
        return ListProductViewModel(repository, adapter)
    }

}