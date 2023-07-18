package com.example.mvvm.api

import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
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

    suspend fun getUser(id: Int): MyResult<UserEntity> {
        try {
            val userEntity: UserEntity = userApiService.getUser(id)
            //try to get and return the MyResult.success(userEntity), which is MyResult<UserEntity>
            //if not exception happens, wrap Nothing in MyResult for Exception so that
            //we can return MyResult<Exception>
            val myResultUserEntity: MyResult<UserEntity> = MyResult.success(userEntity)
            return myResultUserEntity
        } catch (e: Exception) {
            val myResultNothing: MyResult<Nothing> = MyResult.failure(e)
            return myResultNothing
        }
    }

    suspend fun getAllUsers(): MyResult<List<UserEntity>> {
        try {
            val userEntityAll: List<UserEntity> = userApiService.getAllUsers()
            val resultUserEntityAll: MyResult<List<UserEntity>> = MyResult.success(userEntityAll)
            return resultUserEntityAll
        } catch (e: Exception) {
            val myResultNothing: MyResult<List<Nothing>> = MyResult.failure(e)
            return myResultNothing
        }

        /*val jsonString = "[{\"id\": 1,\"name\": \"Uddhav Gautam\"},{\"id\": 2,\"name\": \"Roshani Dahal\"},{\"id\": 3,\"name\": \"Rose Gautam (The Future US President)\"}]"

        // Use Gson to parse the JSON string into a list of UserEntity objects
        val listType = object : TypeToken<List<UserEntity>>() {}.type
        val userEntityAll: List<UserEntity> = Gson().fromJson(jsonString, listType)

        val resultUserEntityAll: MyResult<List<UserEntity>> = MyResult.success(userEntityAll)
        return resultUserEntityAll*/
    }
}