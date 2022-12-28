package com.eschool.android.data.dto.auth

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("reCaptchaToken")
    val reCaptchaToken: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("deviceInfo")
    val deviceInfo: DeviceInfo
)

data class DeviceInfo(
    @SerializedName("os")
    val os: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("model")
    val model: String,
)
