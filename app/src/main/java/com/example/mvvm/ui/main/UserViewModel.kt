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
        result.onSuccess { user: User ->
            // Handle successful response and user object
            _user.value = user
        }.onFailure { exception ->
            // Handle failure or error
            println("Error: ${exception.message}")
        }
    }


}