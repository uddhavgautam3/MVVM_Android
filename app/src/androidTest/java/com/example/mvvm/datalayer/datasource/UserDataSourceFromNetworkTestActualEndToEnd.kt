package com.example.mvvm.datalayer.datasource

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class UserDataSourceFromNetworkTestActualEndToEnd {

    @Inject
    lateinit var userDataSourceFromNetwork: UserDataSourceFromNetwork

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
      }

    @Test
    fun testUserDataSourceFromNetworkNotNull() {
        assertNotNull(userDataSourceFromNetwork)
    }

    //below 4 tests are actual end-to-end testing
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetUserSuccess() {
        runTest(UnconfinedTestDispatcher()) {
            val resultUserEntity = userDataSourceFromNetwork.getUser(1)
            assertNotNull(resultUserEntity)
            resultUserEntity.onSuccess {
                assertEquals(it.id, 1)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetUserFailure() {
        runTest(UnconfinedTestDispatcher()) {
            val resultUserEntity = userDataSourceFromNetwork.getUser(-1)
            assertNotNull(resultUserEntity)
            resultUserEntity.onFailure {
                assertEquals("HTTP 404 Not Found", it.message)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetAllUserSuccess() {
        runTest(UnconfinedTestDispatcher()) {
            val resultAllUsersEntity = userDataSourceFromNetwork.getAllUsers()
            assertNotNull(resultAllUsersEntity)
            resultAllUsersEntity.onSuccess {
                assertNotEquals(it.size, 0)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetAllFailure() {
        runTest(UnconfinedTestDispatcher()) {
            val resultAllUsersEntity = userDataSourceFromNetwork.getAllUsers()
            assertNotNull(resultAllUsersEntity)
            resultAllUsersEntity.onFailure {
                assertEquals("HTTP 404 Not Found", it.message)
            }
        }
    }

}
