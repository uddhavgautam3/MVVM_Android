package com.example.mvvm.datalayer.di

import com.example.mvvm.datalayer.datasource.UserDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class UserDatabaseSourceModuleTest {

    // Use HiltAndroidRule to set up Hilt for the test
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("@AnnotationsLocalDataSource")
    lateinit var localDataSource: UserDataSource

    @Inject
    @Named("@AnnotationsNetworkDataSource")
    lateinit var networkDataSource: UserDataSource

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
    fun testDataSourceInstances() {
        // Use the injected Retrofit instance in your test
        assertNotNull(localDataSource)
        assertNotNull(networkDataSource)
    }

}
