package com.nguyen.basemvvm.ui.listProduct.di

import com.nguyen.basemvvm.ui.base.di.scope.FragmentScope
import com.nguyen.basemvvm.ui.listProduct.view.ListProductFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(
    modules = arrayOf(ListProductModule::class)
)
interface ListProductComponent {
    fun inject(fragment: ListProductFragment): ListProductFragment
}