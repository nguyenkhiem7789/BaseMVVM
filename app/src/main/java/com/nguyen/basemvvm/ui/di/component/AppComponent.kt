package com.nguyen.basemvvm.ui.di.component

import com.nguyen.basemvvm.ui.di.module.ApiClientModule
import com.nguyen.basemvvm.ui.di.module.AppModule
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

//    fun plus(loginModule: LoginModule): LoginComponent
//
//    fun plus(registerModule: RegisterModule): RegisterComponent
//
//    fun plus(homeModule: HomeModule): HomeComponent
}