package com.martha.myapplication.ui.auth.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.martha.myapplication.base.BaseActivity
import com.martha.myapplication.databinding.ActivityRegisterBinding
import com.martha.myapplication.network.Resource
import com.martha.myapplication.network.request.UserRequest
import com.martha.myapplication.ui.auth.viewmodels.UserViewModel

class RegisterActivity : BaseActivity<UserViewModel, ActivityRegisterBinding>(){

    var isUsernameValid = false
    var isPasswordValid = false
    var isConfirmPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initAction()
    }

    fun initView() {
        viewModel.userResponse.observe(this, Observer{
            when(it) {
                is Resource.Success -> {
                    Snackbar.make(bind.llRegister, "Success!, Please sign in with your account", Snackbar.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    Snackbar.make(bind.llRegister, it.message.toString(), Snackbar.LENGTH_LONG).show()
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

        bind.etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (bind.etConfirmPassword.text.trim().toString() == "") {
                    bind.tilConfirmPassword.error = "Password is required"
                    isConfirmPasswordValid = false

                } else if (!bind.etPassword.text.trim().toString().equals(bind.etConfirmPassword.text.trim().toString())) {
                    bind.tilConfirmPassword.error = "Confirm Password is not match"
                    isConfirmPasswordValid = false
                }
                checkValidation()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (bind.etConfirmPassword.text.trim().toString() != "" && bind.etPassword.text.trim().toString().equals(bind.etConfirmPassword.text.trim().toString())) {
                    bind.tilConfirmPassword.error = ""
                    isConfirmPasswordValid = true
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

        bind.etConfirmPassword.setOnFocusChangeListener { view, b ->
            if (bind.etConfirmPassword.text.trim().toString() == "") {
                bind.tilConfirmPassword.error = "Confirm Password is required"
            }
        }

        bind.btnRegister.setOnClickListener {
            var request = UserRequest()
            request.username = bind.etUsername.text.toString().trim()
            request.password = bind.etPassword.text.toString().trim()
            viewModel.register(request)
        }

        bind.ivBack.setOnClickListener { backToPreviousPage() }
    }

    fun checkValidation() {
        bind.btnRegister.isEnabled = isUsernameValid && isPasswordValid && isConfirmPasswordValid
    }

    override fun getViewModel() = UserViewModel::class.java
    override fun getActivityBinding(layoutInflater: LayoutInflater) = ActivityRegisterBinding.inflate(layoutInflater)
}