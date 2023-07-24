package com.example.mvvm.datalayer.datasource

import com.example.mvvm.datalayer.api.UserApiClient
import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit


@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class UserDataSourceFromNetworkTestMocked {

    private lateinit var userDataSourceFromNetworkUsingMock: UserDataSourceFromNetwork

    @Mock
    lateinit var userApiClientMocked: UserApiClient
    @Before
    fun setup() {
        // Initialize Mockito
        MockitoAnnotations.openMocks(this)
        // Initialize the UserDataSourceFromNetwork with the mocked UserApiClient
        userDataSourceFromNetworkUsingMock = UserDataSourceFromNetwork(userApiClientMocked)
    }

    @After
    fun tearDown() {
        MockitoAnnotations.openMocks(this).close()
    }

    @Test
    fun testUserApiClientMockedNotNull() {
        assertNotNull(userApiClientMocked)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetUser() =  runTest(UnconfinedTestDispatcher()) {
        // Mock API response
        val userId = 1
        val expectedUser = UserEntity(id = userId, name = "John Doe")
        val response = MyResult.Success(expectedUser)
        `when`(userApiClientMocked.getUser(userId)).thenReturn(response)

        // Call the method under test
        val result = userDataSourceFromNetworkUsingMock.getUser(userId)

        // Assert the result
        assertEquals(response, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetAllUsers() =  runTest(UnconfinedTestDispatcher()) {
        // Mock API response
        val expectedUsers = listOf(
            UserEntity(id = 1, name = "John Doe"),
            UserEntity(id = 2, name = "Jane Smith")
        )
        val response = MyResult.Success(expectedUsers)
        `when`(userApiClientMocked.getAllUsers()).thenReturn(response)

        // Call the method under test
        val result = userDataSourceFromNetworkUsingMock.getAllUsers()

        // Assert the result
        assertEquals(response, result)
    }

}
