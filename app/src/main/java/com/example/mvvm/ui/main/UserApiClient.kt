package com.example.mvvm.ui.main

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserApiClient {
    private val userApiService: UserApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.19:3000/") // Replace with the actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userApiService = retrofit.create(UserApiService::class.java)
    }

    suspend fun getUser(id: Int): User {
        return userApiService.getUserById(id)
    }
}