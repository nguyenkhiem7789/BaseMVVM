package com.nguyen.basemvvm

import android.app.Application
import android.content.Context
import com.nguyen.basemvvm.ui.base.di.component.AppComponent
import com.nguyen.basemvvm.ui.base.di.component.DaggerAppComponent
import com.nguyen.basemvvm.ui.base.di.module.AppModule

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        fun get(context: Context): BaseApplication {
            return context.applicationContext as BaseApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}