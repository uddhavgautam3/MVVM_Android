package com.example.mvvm.datalayer.datasource

import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity

interface UserDataSource {
    suspend fun getUser(id: Int): MyResult<UserEntity>
    suspend fun getAllUsers(): MyResult<List<UserEntity>>
}