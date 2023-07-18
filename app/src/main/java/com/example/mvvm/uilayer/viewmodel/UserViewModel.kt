package com.example.mvvm.uilayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import com.example.mvvm.domainlayer.usercase.FetchAllUserUseCase
import com.example.mvvm.domainlayer.usercase.FetchUserUseCase
import com.example.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase,
    private val fetchAllUserUseCase: FetchAllUserUseCase,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _userEntity = MutableLiveData<UserEntity?>()
    val userEntity: LiveData<UserEntity?> = _userEntity

    private val _userEntityList = MutableLiveData<List<UserEntity?>?>()
    val userEntityList: LiveData<List<UserEntity?>?> = _userEntityList

    suspend fun loadUserById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            //I could use MyResult provided by the system
            val myResult: MyResult<UserEntity> = fetchUserUseCase.getUser(id, networkUtils)
            //inside onSuccess, I can send any T type
            val myResultUserEntity: MyResult<UserEntity> =
                myResult.onSuccess { userEntity: UserEntity ->
                    // Handle successful response and userEntity object
                    withContext(Dispatchers.Main) {
                        //always update LiveData in Main thread
                        _userEntity.value = userEntity
                    }
                }.onFailure { exception ->
                    // Handle failure on error
                    println("Error: ${exception.message}")
                }
        }
    }

    suspend fun loadAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val myResult: MyResult<List<UserEntity>> = fetchAllUserUseCase.getAllUsers(networkUtils)
            val myResultUserEntity: MyResult<List<UserEntity>> =
                myResult.onSuccess { userEntityList: List<UserEntity> ->
                    withContext(Dispatchers.Main) {
                        //always update LiveData in Main thread
                        _userEntityList.value = userEntityList
                    }
                }.onFailure { exception ->
                    println("Error: ${exception.message}")
                }
        }

    }

}