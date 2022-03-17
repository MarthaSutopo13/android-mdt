package com.martha.myapplication.network.api

import com.martha.myapplication.network.request.UserRequest
import com.martha.myapplication.network.responses.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(
        @Body user: UserRequest
    ): UserResponse

    @POST("register")
    suspend fun register(
        @Body user: UserRequest
    ): UserResponse
}