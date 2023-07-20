package com.example.mvvm.datalayer.di

import com.example.mvvm.api.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserApiClientModule {
    @Provides
    fun provideUserApiClient(): UserApiClient {
        return UserApiClient()
    }
}