package com.martha.myapplication.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TransferRequest {
    @SerializedName("receipientAccountNo")
    @Expose
    var receipientAccountNo: String? = null

    @SerializedName("amount")
    @Expose
    var amount: Double? = null

    @SerializedName("description")
    @Expose
    var description: String? = null
}