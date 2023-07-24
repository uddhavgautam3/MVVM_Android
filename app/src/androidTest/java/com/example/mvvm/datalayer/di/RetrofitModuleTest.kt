package com.example.mvvm.datalayer.di

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject

@HiltAndroidTest
class RetrofitModuleTest {

    // Use HiltAndroidRule to set up Hilt for the test
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var retrofit: Retrofit

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
        assertNotNull(retrofit)
    }

    @Test
    fun testRetrofitBaseUrl() {
        // Check the base URL set in the Retrofit instance
        val expectedBaseUrl = "http://192.168.0.19:3000/"
        assertEquals(expectedBaseUrl, retrofit.baseUrl().toString())
    }

}
