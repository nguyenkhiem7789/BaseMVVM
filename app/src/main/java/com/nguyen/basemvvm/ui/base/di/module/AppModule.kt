package com.nguyen.basemvvm.ui.base.di.module

import android.app.Application
import com.nguyen.basemvvm.utils.SharePrefsUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by apple on 9/9/17.
 */
@Module
class AppModule(var appContext: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return appContext
    }

    @Provides
    @Singleton
    fun providePrefsUtil(): SharePrefsUtils {
        return SharePrefsUtils.getInstance(appContext)
    }
}