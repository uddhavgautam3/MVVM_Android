package com.example.mvvm.datalayer.repository

import com.example.mvvm.datalayer.datasource.UserDataSource
import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import com.example.utils.NetworkUtils
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(
    @Named("@AnnotationsLocalDataSource") private val userDataSourceLocalDB: UserDataSource,
    @Named("@AnnotationsNetworkDataSource") private val userDataSourceNetwork: UserDataSource
) {
    suspend fun getUser(
        id: Int,
        networkUtils: NetworkUtils
    ): MyResult<UserEntity> {
        return if (networkUtils.isNetworkAvailable()) {
            userDataSourceNetwork.getUser(id)
        } else {
            userDataSourceLocalDB.getUser(id)
        }
    }

    suspend fun getAllUsers(
        networkUtils: NetworkUtils
    ): MyResult<List<UserEntity>> {
        return if (networkUtils.isNetworkAvailable()) {
            userDataSourceNetwork.getAllUsers()
        } else {
            userDataSourceLocalDB.getAllUsers()
        }
    }
}
