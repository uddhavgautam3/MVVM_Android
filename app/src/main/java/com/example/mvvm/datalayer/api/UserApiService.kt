package com.example.mvvm.datalayer.api

import com.example.mvvm.datalayer.model.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserEntity

    @GET("users")
    suspend fun getAllUsers(): List<UserEntity>
}