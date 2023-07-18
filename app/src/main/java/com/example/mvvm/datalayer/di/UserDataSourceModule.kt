package com.example.mvvm.datalayer.di

import com.example.mvvm.datalayer.datasource.UserDataSource
import com.example.mvvm.datalayer.datasource.UserDataSourceFromLocalDB
import com.example.mvvm.datalayer.datasource.UserDataSourceFromNetwork
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
    @Named("@AnnotationsLocalDataSource") //makes unique
    fun provideUserDataSourceLocalDB(userDataSourceFromLocalDB: UserDataSourceFromLocalDB): UserDataSource {
        return userDataSourceFromLocalDB
    }

    @Singleton
    @Provides
    @Named("@AnnotationsNetworkDataSource")
    fun provideUserDataSourceNetwork(userDataSourceFromNetwork: UserDataSourceFromNetwork): UserDataSource {
        return userDataSourceFromNetwork
    }
}



