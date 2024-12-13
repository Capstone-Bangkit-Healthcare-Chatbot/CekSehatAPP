package com.example.loginregister_ceksehat.data

import com.google.gson.annotations.SerializedName

data class ChatHistoryResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("input")
    val input: String,
    @SerializedName("response")
    val response: String,
    @SerializedName("timestamp")
    val timestamp: String
)