package com.martha.myapplication.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Payees {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("accountNo")
    @Expose
    var accountNo: String? = null
}