package com.nguyen.basemvvm.ui.authentication.di

import com.nguyen.basemvvm.data.remote.network.ApiService
import com.nguyen.basemvvm.data.repository.LoginRepository
import com.nguyen.basemvvm.ui.authentication.viewmodel.LoginViewModel
import com.nguyen.basemvvm.ui.base.di.scope.FragmentScope
import com.nguyen.basemvvm.ui.base.view.BaseActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule {

    @Provides
    @FragmentScope
    fun provideLoginRepository(apiService: ApiService) : LoginRepository {
        return LoginRepository(apiService)
    }

    @Provides
    @FragmentScope
    fun provideLoginViewModel(loginRepository: LoginRepository): LoginViewModel {
        return LoginViewModel(loginRepository)
    }

}