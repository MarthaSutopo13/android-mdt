package com.martha.myapplication.network.api

import com.martha.myapplication.network.responses.BalanceResponse
import retrofit2.http.GET

interface BalanceApi {
    @GET("balance")
    suspend fun balance(
    ): BalanceResponse
}