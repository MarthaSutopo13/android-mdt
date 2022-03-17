package com.martha.myapplication.ui.auth.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martha.myapplication.data.repository.UserRepository
import com.martha.myapplication.network.Resource
import com.martha.myapplication.network.request.UserRequest
import com.martha.myapplication.network.responses.UserResponse
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepo: UserRepository
) : ViewModel() {

    private val _userResponse: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val userResponse: LiveData<Resource<UserResponse>>
        get() = _userResponse

    fun login(user: UserRequest)
            = viewModelScope.launch {
        _userResponse.value = userRepo.login(user)
    }

    fun register(user: UserRequest)
            = viewModelScope.launch {
        _userResponse.value = userRepo.register(user)
    }

    fun setToken(token: String) = viewModelScope.launch {
        userRepo.setToken(token)
    }

    fun setUsername(username: String) = viewModelScope.launch {
        userRepo.setUsername(username)
    }

    fun setAccountNo(acno: String) = viewModelScope.launch {
        userRepo.setAccountNo(acno)
    }
}