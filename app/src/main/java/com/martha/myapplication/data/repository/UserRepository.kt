package com.martha.myapplication.data.repository

import com.martha.myapplication.base.BaseRepository
import com.martha.myapplication.network.api.AuthApi
import com.martha.myapplication.network.request.UserRequest
import com.martha.myapplication.util.DataPreferences

class UserRepository(
    val service: AuthApi,
    private val preference: DataPreferences
) : BaseRepository() {

    suspend fun login(user: UserRequest) = handlingApiCall {
        service.login(user)
    }

    suspend fun register(user: UserRequest) = handlingApiCall {
        service.register(user)
    }

    suspend fun setToken(token: String) {
        preference.setToken(token)
    }

    suspend fun setUsername(username: String) {
        preference.setUsername(username)
    }

    suspend fun setAccountNo(accountNo: String) {
        preference.setAccountNo(accountNo)
    }

}