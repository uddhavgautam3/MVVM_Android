package com.example.mvvm.domainlayer.usercase

import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import com.example.mvvm.datalayer.repository.UserRepository
import com.example.utils.NetworkUtils
import javax.inject.Inject

class FetchAllUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun getAllUsers(
        networkUtils: NetworkUtils
    ): MyResult<List<UserEntity>> = userRepository.getAllUsers(networkUtils)
}