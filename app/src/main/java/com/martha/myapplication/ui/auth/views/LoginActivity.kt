package com.martha.myapplication.ui.auth.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import com.martha.myapplication.base.BaseActivity
import com.martha.myapplication.databinding.ActivityLoginBinding
import com.martha.myapplication.network.Resource
import com.martha.myapplication.network.request.UserRequest
import com.martha.myapplication.ui.MainActivity
import com.martha.myapplication.ui.auth.viewmodels.UserViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData

class LoginActivity : BaseActivity<UserViewModel, ActivityLoginBinding>(){
    var isUsernameValid = false
    var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initAction()
    }

    fun initView() {
        prefs.getToken.asLiveData().observe(this, Observer {
            if (it != "") goToActivity(MainActivity::class.java, null, true)
        })

        viewModel.userResponse.observe(this, Observer{
            when(it) {
                is Resource.Success -> {
                    it.value.token?.let { it1 -> viewModel.setToken(it1) }
                    it.value.username?.let { it1 -> viewModel.setUsername(it1) }
                    it.value.accountNo?.let { it1 -> viewModel.setAccountNo(it1) }
                    goToActivity(MainActivity::class.java, null, true)
                }
                is Resource.Failure -> {
                    Snackbar.make(bind.llLogin, "Login Failure", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun initAction() {
        bind.etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (bind.etUsername.text.trim().toString() == "") {
                    bind.tilUsername.error = "Username is required"
                    isUsernameValid = false
                }
                checkValidation()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (bind.etUsername.text.trim().toString() != "") {
                    bind.tilUsername.error = ""
                    isUsernameValid = true
                }
            }
        })

        bind.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (bind.etPassword.text.trim().toString() == "") {
                    bind.tilPassword.error = "Password is required"
                    isPasswordValid = false
                }
                checkValidation()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (bind.etPassword.text.trim().toString() != "") {
                    bind.tilPassword.error = ""
                    isPasswordValid = true
                }
            }
        })

        bind.etUsername.setOnFocusChangeListener { view, b ->
            if (bind.etUsername.text.trim().toString() == "") {
                bind.tilUsername.error = "Username is required"
            }
        }

        bind.etPassword.setOnFocusChangeListener { view, b ->
            if (bind.etPassword.text.trim().toString() == "") {
                bind.tilPassword.error = "Password is required"
            }
        }

        bind.btnLogin.setOnClickListener {
            var request = UserRequest()
            request.username = bind.etUsername.text.toString().trim()
            request.password = bind.etPassword.text.toString().trim()
            viewModel.login(request)
        }

        bind.btnRegister.setOnClickListener {
            goToActivity(RegisterActivity::class.java, null, false)
        }
    }

    fun checkValidation() {
        bind.btnLogin.isEnabled = isUsernameValid && isPasswordValid
    }

    override fun getViewModel() = UserViewModel::class.java
    override fun getActivityBinding(layoutInflater: LayoutInflater) = ActivityLoginBinding.inflate(layoutInflater)
}