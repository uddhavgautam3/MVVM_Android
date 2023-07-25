package com.example.mvvm.datalayer.datasource

import com.example.mvvm.datalayer.api.UserApiService
import com.example.mvvm.datalayer.model.UserEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class UserDataSourceFromNetworkTestMocked {

    private lateinit var userApiService: UserApiService

    @Before
    fun setUp() {
        userApiService = mockk()
    }

    @Test
    fun testUserApiServiceNotNull() {
        assertNotNull(userApiService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetUser() {
        // Create a mock instance of the UserApiService
        val userApiService = mockk<UserApiService>()

        val userId = 123
        val userEntity = UserEntity(id = userId, name = "John Doe")
        /*
        coEvery is a function provided by the mockk library, which is a popular Kotlin mocking
        library used for creating mock objects and stubbing behaviors in unit tests.
        In the context of Kotlin coroutines, coEvery is specifically used for stubbing suspending functions.
        When writing unit tests for code that involves suspending functions, we need to handle
        coroutines and their asynchronous behavior. The coEvery function allows us to stub the
        behavior of a suspending function and define what it should return when called.
        The basic syntax of coEvery is as follows:
            coEvery { suspendingFunction() } returns value
         */
        coEvery { userApiService.getUser(userId) } returns userEntity

        runTest(UnconfinedTestDispatcher()) {
            val result = userApiService.getUser(userId)
            assertEquals(userEntity, result)
            coVerify { userApiService.getUser(userId) }
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetAllUsers() {
        // Create a mock instance of the UserApiService
        val userApiService = mockk<UserApiService>()

        // Define the behavior of the getAllUsers method
        val userList = listOf(
            UserEntity(1, "User 1"),
            UserEntity(2, "User 2"),
            UserEntity(3, "User 3")
        )
        coEvery { userApiService.getAllUsers() } returns userList

        runTest(UnconfinedTestDispatcher()) {
            val result = userApiService.getAllUsers()
            assertEquals(userList, result)
            coVerify { userApiService.getAllUsers() }
        }

    }

}