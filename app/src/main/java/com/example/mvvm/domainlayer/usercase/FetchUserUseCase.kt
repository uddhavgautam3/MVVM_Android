package com.example.mvvm.domainlayer.usercase

import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import com.example.mvvm.datalayer.repository.UserRepository
import com.example.utils.NetworkUtils
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FetchUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getUser(
        id: Int,
        networkUtils: NetworkUtils
    ): MyResult<UserEntity> = userRepository.getUser(id, networkUtils)
}