package com.example.mvvm.domainlayer.usercase

import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import com.example.mvvm.datalayer.repository.UserRepository
import com.example.utils.NetworkUtils
import dagger.Reusable
import javax.inject.Inject

@Reusable
/* annotation on the FetchAllUserUseCase class. This annotation is useful
 when you want Dagger to re-use the same instance of the class within
 the same component scope, rather than creating a new instance every time it's injected.
 */
class FetchAllUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getAllUsers(
        networkUtils: NetworkUtils
    ): MyResult<List<UserEntity>> = userRepository.getAllUsers(networkUtils)
}