package com.example.mvvm.ui.main

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserApiClient {
    private val userApiService: UserApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.19:3000/") // Replace with the actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userApiService = retrofit.create(UserApiService::class.java)
    }

    suspend fun getUser(
        userId: Int,
        callback: (Response<User>) -> Unit
    ) {
        val call = userApiService.getUser(userId) //call: Call<User> part of retrofit
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                callback(response)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Handle failure
            }
        })
    }

}