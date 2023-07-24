package com.example.mvvm.datalayer.api

import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class UserApiClient @Inject constructor(
    retrofit: Retrofit
) {
    private val userApiService: UserApiService =
        retrofit.create(UserApiService::class.java)

    /*suspend fun getUser(id: Int): MyResult<UserEntity> {
        return try {
            val userEntity: UserEntity = userApiService.getUser(id)
            MyResult.success(userEntity)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }*/

    suspend fun getUser(id: Int): MyResult<UserEntity> {
        return runCatching {
            userApiService.getUser(id)
        }.fold(
            onSuccess = { MyResult.success(it) },
            /* If the throwable is an instance of Kotlin.Exception, we use it directly.
            Otherwise, we create a new Exception instance with the original throwable as its cause.
             */
            onFailure = { throwable ->
                val exception = throwable as? Exception ?: Exception(throwable)
                exception.message?.let { Timber.tag(TAG).d(it) }
                MyResult.Failure(exception)
            }
        )
    }

    suspend fun getAllUsers(): MyResult<List<UserEntity>> {
        return try {
            val userEntityAll: List<UserEntity> = userApiService.getAllUsers()
            MyResult.success(userEntityAll)
        } catch (e: Exception) {
            MyResult.failure(e)
        }
    }

    companion object {
        const val TAG = "UserApiClient"
    }

}