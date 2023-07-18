package com.example.mvvm.usercase

import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.User
import com.example.mvvm.repository.UserRepository
import com.example.mvvm.utils.NetworkUtils
import javax.inject.Inject

class FetchAllUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun getAllUsers(networkUtils: NetworkUtils, _userList: MutableLiveData<List<User?>?>) =
        userRepository.getAllUsers(networkUtils, _userList)
}