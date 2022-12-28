package com.eschool.android.data.repository

import com.eschool.android.data.dto.ResponseBase
import com.eschool.android.data.dto.auth.SignUpRequest
import com.eschool.android.data.dto.auth.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthRepository {
    @POST("public/auth/sign-up")
    @Headers("Content-Type: application/json")
    suspend fun signUp(
        @Body body: SignUpRequest
    ): Response<ResponseBase<SignUpResponse>>
}