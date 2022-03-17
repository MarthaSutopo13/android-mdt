package com.martha.myapplication.network.api

import com.martha.myapplication.network.request.TransferRequest
import com.martha.myapplication.network.responses.PayeesResponse
import com.martha.myapplication.network.responses.TransactionResponse
import com.martha.myapplication.network.responses.TransferResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TransactionApi {
    @GET("transactions")
    suspend fun transactionHistory(
    ): TransactionResponse

    @POST("transfer")
    suspend fun transfer(
        @Body tr: TransferRequest
    ): TransferResponse

    @GET("payees")
    suspend fun payees(
    ): PayeesResponse

}