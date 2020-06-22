package com.nguyen.basemvvm.ui.base.di.module
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nguyen.basemvvm.data.remote.network.ApiService
import com.nguyen.basemvvm.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by apple on 9/11/17.
 */
@Module
class ApiClientModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        var builder = OkHttpClient.Builder()
        var logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        builder.connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
            .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        var builder = Retrofit.Builder()
        builder.client(okHttpClient)
            .baseUrl(Constant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

}