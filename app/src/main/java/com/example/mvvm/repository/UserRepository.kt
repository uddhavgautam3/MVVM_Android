package com.example.mvvm.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.datasource.UserDataSource
import com.example.mvvm.model.Result
import com.example.mvvm.model.User
import com.example.mvvm.utils.NetworkUtils
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(
    @Named("@LocalDBDataSource") private val userUserDataSourceLocalDB: UserDataSource,
    @Named("@NetworkDataSource") private val userUserDataSourceNetwork: UserDataSource
) {
    suspend fun getUser(
        id: Int,
        networkUtils: NetworkUtils
    ): Result<User> {
        return if (networkUtils.isNetworkAvailable()) {
            userUserDataSourceNetwork.getUser(id)
        } else {
            userUserDataSourceLocalDB.getUser(id)
        }
    }

    suspend fun getAllUsers(networkUtils: NetworkUtils, _userList: MutableLiveData<List<User?>?>) {
        if (networkUtils.isNetworkAvailable()) {
            userUserDataSourceNetwork.getAllUsers(_userList)
        } else {
            userUserDataSourceLocalDB.getAllUsers(_userList)
        }
    }
}
