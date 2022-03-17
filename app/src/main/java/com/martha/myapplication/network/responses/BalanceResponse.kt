package com.martha.myapplication.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BalanceResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("accountNo")
    @Expose
    var accountNo: String? = null

    @SerializedName("balance")
    @Expose
    var balance: Double? = null
}