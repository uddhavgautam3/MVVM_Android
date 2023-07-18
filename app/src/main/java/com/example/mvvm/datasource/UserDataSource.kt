package com.example.mvvm.datasource

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.Result
import com.example.mvvm.model.User


interface UserDataSource {
    suspend fun getUser(id: Int): Result<User>
    suspend fun getAllUsers(_userList: MutableLiveData<List<User?>?>)
}