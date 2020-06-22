package com.nguyen.basemvvm.ui.authentication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nguyen.basemvvm.BaseApplication
import com.nguyen.basemvvm.R
import com.nguyen.basemvvm.data.remote.request.LoginRequest
import com.nguyen.basemvvm.ui.authentication.di.LoginModule
import com.nguyen.basemvvm.ui.authentication.viewmodel.LoginViewModel
import com.nguyen.basemvvm.ui.base.view.BaseFragment
import com.nguyen.basemvvm.utils.DisposableManager
import com.nguyen.basemvvm.utils.SnackBar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.view.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        setupComponent()
        setupBinding()
        initView(view)
        return view
    }

    private fun initView(view: View) {
        view.loginButton.setOnClickListener {
            val userName = view.nameEditText.text.toString()
            val password = view.passEditText.text.toString()
            val loginRequest = LoginRequest(userName, password)
            viewModel.login(loginRequest)
        }
    }

    private fun setupBinding() {
        DisposableManager.add(viewModel
            .isLoading
            .observeOn(Schedulers.io())
            .subscribe {
                if(it) {
                    //start loading
                } else {
                    //stop loading
                }
            })
        DisposableManager.add(viewModel
            .errorMsg
            .observeOn(Schedulers.io())
            .subscribe {
                SnackBar.show(view!!, it, false)
            })
        DisposableManager.add(viewModel
            .loginResponse
            .observeOn(Schedulers.io())
            .subscribe {

            })
    }

    private fun setupComponent() {
        context?.let {
            BaseApplication.get(it)
                .appComponent
                .plus(LoginModule(activity))
                .inject(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}