package com.example.mvvm

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.mvvm.datalayer.db.UserDatabase
import com.example.mvvm.datalayer.model.UserEntity
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * I could also make this to test UserDao. It is similar
 * CustomTestRunner should be used in build.gradle.kts file; otherwise hilt dependency injection won't work
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@LargeTest
class MvvmApplicationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    /*@Inject
    lateinit var userDao: UserDao*/
    @Inject
    lateinit var userDatabase: UserDatabase

    // Lazy initialization of test data using Gson and runBlockingTest
    private val jsonData by lazy {
        """
            [
                {
                    "id": 1,
                    "name": "Uddhav P Gautam"
                },
                {
                    "id": 2,
                    "name": "Roshani P Dahal"
                },
                {
                    "id": 3,
                    "name": "Rose P Gautam (The Future US President)"
                }
            ]
        """.trimIndent()
    }

    private val userList by lazy {
        Gson().fromJson(jsonData, Array<UserEntity>::class.java).toList()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        // Make sure to inject dependencies before running the tests
        hiltRule.inject()

        // Insert the test data into the database using runTest(UnconfinedTestDispatcher()) instead of runBlockingTest
        runTest(UnconfinedTestDispatcher()) {
            userDatabase.userDao().insertUsers(userList)
        }
    }

    @Test
    fun testInsertedDataInDatabase() {
        // Query the database to retrieve the inserted data
        val usersFromDb = userDatabase.userDao().getAllUsers()

        // Verify that the retrieved data matches the expected data
        val expectedUsers = listOf(
            UserEntity(1, "Uddhav P Gautam"),
            UserEntity(2, "Roshani P Dahal"),
            UserEntity(3, "Rose P Gautam (The Future US President)")
        )

        assertEquals(expectedUsers, usersFromDb)
    }
}