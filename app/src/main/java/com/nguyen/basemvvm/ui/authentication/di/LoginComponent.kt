package com.nguyen.basemvvm.ui.authentication.di

import com.nguyen.basemvvm.ui.authentication.view.LoginFragment
import com.nguyen.basemvvm.ui.base.di.scope.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = arrayOf(LoginModule::class)
)
interface LoginComponent {
    fun inject(loginFragment: LoginFragment): LoginFragment
}