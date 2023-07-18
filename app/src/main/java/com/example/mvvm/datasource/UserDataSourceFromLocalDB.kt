package com.example.mvvm.datasource

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.Result
import com.example.mvvm.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSourceFromLocalDB @Inject constructor() : UserDataSource {
    override suspend fun getUser(id: Int): Result<User> {
        return Result.success(User(id, "From Local DB"))
    }

    override fun getAllUsers(_userList: MutableLiveData<List<User?>?>) {
        TODO()
    }

}
