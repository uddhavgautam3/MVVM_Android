package com.example.mvvm.usercase

import com.example.mvvm.model.Result
import com.example.mvvm.model.User
import com.example.mvvm.repository.UserRepository
import com.example.mvvm.utils.NetworkUtils
import javax.inject.Inject

class FetchUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getUser(
        id: Int,
        networkUtils: NetworkUtils
    ): Result<User> = userRepository.getUser(id, networkUtils)
}