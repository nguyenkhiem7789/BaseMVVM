package com.nguyen.basemvvm.ui.base.di.component

import com.nguyen.basemvvm.ui.authentication.di.LoginComponent
import com.nguyen.basemvvm.ui.authentication.di.LoginModule
import com.nguyen.basemvvm.ui.base.di.module.ApiClientModule
import com.nguyen.basemvvm.ui.base.di.module.AppModule
import com.nguyen.basemvvm.ui.listProduct.di.ListProductComponent
import com.nguyen.basemvvm.ui.listProduct.di.ListProductModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by apple on 9/9/17.
 */
@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        ApiClientModule::class)
)
interface AppComponent {
    fun plus(module: LoginModule): LoginComponent
    fun plus(module: ListProductModule): ListProductComponent
}