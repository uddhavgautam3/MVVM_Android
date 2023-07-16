package com.example.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val userApiClient = UserApiClient()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    suspend fun loadUser(id: Int) {
        _user.value = userApiClient.getUser(id)
    }
}