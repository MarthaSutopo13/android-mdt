package com.martha.myapplication.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.martha.myapplication.network.model.Transaction

class TransactionResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: MutableList<Transaction>? = null
}