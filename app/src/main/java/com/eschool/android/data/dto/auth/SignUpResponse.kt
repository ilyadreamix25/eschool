package com.eschool.android.data.dto.auth

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("accountId")
    val accountId: String,
    @SerializedName("auth")
    val auth: Auth,
    @SerializedName("profile")
    val profile: Profile,
    @SerializedName("schoolInfo")
    val schoolInfo: SchoolInfo
)

data class Auth(
    @SerializedName("lastSignInAt")
    val lastSignInAt: Int,
    @SerializedName("sessionToken")
    val sessionToken: String,
    @SerializedName("signedUpAt")
    val signedUpAt: Long
)

data class Profile(
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("role")
    val role: Int
)

data class SchoolInfo(
    @SerializedName("classId")
    val classId: String,
    @SerializedName("schoolId")
    val schoolId: String
)
