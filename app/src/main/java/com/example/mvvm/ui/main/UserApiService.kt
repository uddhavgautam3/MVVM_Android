package com.example.mvvm.ui.main

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
   /* @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Call<User>*/

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int):User

}