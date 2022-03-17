package com.martha.myapplication.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Transaction {
    @SerializedName("transactionId")
    @Expose
    var transactionId: String? = null

    @SerializedName("amount")
    @Expose
    var amount: Double? = null

    @SerializedName("transactionDate")
    @Expose
    var transactionDate: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("transactionType")
    @Expose
    var transactionType: String? = null

    @SerializedName("receipient")
    @Expose
    var receipient: Receipient? = null
}