package com.martha.myapplication.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("accountNo")
    @Expose
    var accountNo: String? = null
}