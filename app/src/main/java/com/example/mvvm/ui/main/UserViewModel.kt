package com.example.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val userApiClient = UserApiClient()

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    /*suspend fun loadUser(id: Int) {
        userApiClient.getUser(id) { response: Response<User> ->
            run {
                if (response.isSuccessful) {
                    val user = response.body()
                    _user.value = user
                } else {
                    // Handle unsuccessful response or error
                }
            }
        }
    }*/

    suspend fun loadUser(id: Int) {
        //I could use Result provided by the system
        val result: Result<User> =  userApiClient.getUser(id)
        //inside onSuccess, I can send any T type
        val resultUser: Result<User> = result.onSuccess { user: User ->
            // Handle successful response and user object
            _user.value = user //this is what returns void (i.e., Unit)
        }.onFailure { exception ->
            // Handle failure on error
            println("Error: ${exception.message}")
        }
    }


}