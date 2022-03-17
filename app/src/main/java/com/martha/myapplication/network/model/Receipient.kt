package com.martha.myapplication.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Receipient {
    @SerializedName("accountNo")
    @Expose
    var accountNo: String? = null

    @SerializedName("accountHolder")
    @Expose
    var accountHolder: String? = null
}