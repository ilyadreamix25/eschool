package com.eschool.android.data.di

import com.eschool.android.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    fun provideService() = ApiModule.provideService(AuthRepository::class.java)
}