package com.example.mvvm.datalayer.di

import android.content.Context
import com.example.mvvm.datalayer.dao.UserDao
import com.example.mvvm.datalayer.db.UserDatabase
import com.example.mvvm.datalayer.model.UserEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class UserDatabaseModuleTest {

    // Use HiltAndroidRule to set up Hilt for the test
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @ApplicationContext
    lateinit var context: Context

    @Inject
    lateinit var userDatabase: UserDatabase

    @Inject
    lateinit var userDao: UserDao


    @Before
    fun setUp() {
        // Inject dependencies before running each test
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        //no need to do anything
    }

    @Test
    fun testRetrofitInstance() {
        // Use the injected Retrofit instance in your test
        assertNotNull(userDatabase)
        assertNotNull(userDao)
    }

    @Test
    fun testUserDaoInsertAndRead() {
        // Test inserting and reading data using UserDao
        val user = UserEntity(id = 1, name = "John Doe")

        userDao.insertUser(user)
        val retrievedUser = userDao.getUser(user.id)

        assertNotNull(retrievedUser)
        assertEquals(retrievedUser.id, user.id)
        assertEquals(retrievedUser.name, user.name)
    }

    @Test
    fun testUserDatabaseInsertAndRead() {
        // Test inserting and reading data using UserDao
        val user = UserEntity(id = 1, name = "John Doe")

        userDatabase.userDao().insertUser(user)
        val retrievedUser = userDao.getUser(user.id)

        assertNotNull(retrievedUser)
        assertEquals(retrievedUser.id, user.id)
        assertEquals(retrievedUser.name, user.name)
    }

}
