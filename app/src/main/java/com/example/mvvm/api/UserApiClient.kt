package com.example.mvvm.api

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.Result
import com.example.mvvm.model.User
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

    suspend fun getUser(id: Int): Result<User> {
        try {
            val user: User = userApiService.getUser(id)
            //try to get and return the Result.success(user), which is Result<User>
            //if not exception happens, wrap Nothing in Result for Exception so that
            //we can return Result<Exception>
            val resultUser: Result<User> = Result.success(user)
            return resultUser
        } catch (e: Exception) {
            val resultNothing: Result<Nothing> = Result.failure(e)
            return resultNothing
        }
    }

    fun getAllUsers(_userList: MutableLiveData<List<User?>?>) {
        userApiService.getAllUsers().enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val userList: List<User>? = response.body()
                    _userList.value = userList
                } else {
                    // Handle the error response
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // Handle the network failure
            }
        }
        )
    }
}