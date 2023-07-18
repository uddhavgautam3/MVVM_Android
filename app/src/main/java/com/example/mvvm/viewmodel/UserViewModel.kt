package com.example.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.Result
import com.example.mvvm.model.User
import com.example.mvvm.usercase.FetchAllUserUseCase
import com.example.mvvm.usercase.FetchUserUseCase
import com.example.mvvm.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase,
    private val fetchAllUserUseCase: FetchAllUserUseCase,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _userList = MutableLiveData<List<User?>?>()
    val userList: LiveData<List<User?>?> = _userList

    suspend fun loadUserById(id: Int) {
        //I could use Result provided by the system
        val result: Result<User> = fetchUserUseCase.getUser(id, networkUtils)
        //inside onSuccess, I can send any T type
        val resultUser: Result<User> = result.onSuccess { user: User ->
            // Handle successful response and user object
            _user.value = user //this is what returns void (i.e., Unit)
        }.onFailure { exception ->
            // Handle failure on error
            println("Error: ${exception.message}")
        }
    }

    fun getAllUsers() {
        fetchAllUserUseCase.getAllUsers(networkUtils, _userList)
    }

}