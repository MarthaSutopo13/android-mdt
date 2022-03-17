package com.martha.myapplication.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.martha.myapplication.network.model.Payees

class PayeesResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: MutableList<Payees>? = null
}