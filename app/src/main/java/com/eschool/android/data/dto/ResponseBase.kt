package com.eschool.android.data.dto

import com.google.gson.annotations.SerializedName

data class ResponseBase<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("response")
    val response: T?
)
