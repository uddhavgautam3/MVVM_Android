package com.example.mvvm.di

import com.example.mvvm.datasource.UserDataSource
import com.example.mvvm.datasource.UserDataSourceFromLocalDB
import com.example.mvvm.datasource.UserDataSourceFromNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataSourceModule {
    @Singleton
    @Provides
    @Named("@LocalDBDataSource") //make unique
    fun provideUserDataSourceLocalDB(userDataSourceFromLocalDB: UserDataSourceFromLocalDB): UserDataSource {
        return userDataSourceFromLocalDB
    }

    @Singleton
    @Provides
    @Named("@NetworkDataSource") //makes unique
    fun provideUserDataSourceNetwork(userDataSourceFromNetwork: UserDataSourceFromNetwork): UserDataSource {
        return userDataSourceFromNetwork
    }
}



