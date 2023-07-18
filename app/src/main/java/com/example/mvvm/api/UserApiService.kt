package com.example.mvvm.api

import com.example.mvvm.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @GET("users")
    fun getAllUsers(): Call<List<User>>
}