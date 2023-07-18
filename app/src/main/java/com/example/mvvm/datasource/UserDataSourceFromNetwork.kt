package com.example.mvvm.datasource

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.api.UserApiClient
import com.example.mvvm.model.Result
import com.example.mvvm.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSourceFromNetwork @Inject constructor() : UserDataSource {
    private val userApiClient = UserApiClient()
    override suspend fun getUser(id: Int): Result<User> {
        return userApiClient.getUser(id)
    }

    override suspend fun getAllUsers(_userList: MutableLiveData<List<User?>?>) {
        userApiClient.getAllUsers(_userList)
    }

}