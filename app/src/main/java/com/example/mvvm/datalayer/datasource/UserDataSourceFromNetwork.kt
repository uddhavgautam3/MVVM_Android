package com.example.mvvm.datalayer.datasource

import com.example.mvvm.datalayer.api.UserApiClient
import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSourceFromNetwork @Inject constructor(
    private val userApiClient: UserApiClient
) : UserDataSource {
    override suspend fun getUser(id: Int): MyResult<UserEntity> {
        return userApiClient.getUser(id)
    }

    override suspend fun getAllUsers(): MyResult<List<UserEntity>> {
        return userApiClient.getAllUsers()
    }

}