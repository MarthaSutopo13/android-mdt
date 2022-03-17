package com.martha.myapplication.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TransferResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null
}