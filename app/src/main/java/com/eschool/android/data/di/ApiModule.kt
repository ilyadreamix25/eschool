package com.eschool.android.data.di

import com.eschool.android.data.common.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiModule {
    fun <T> provideService(service: Class<T>): T =
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .apply { addInterceptor(RequestInterceptor()) }
                    .build()
            )
            .baseUrl("http://192.168.0.108:7285")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service)
}