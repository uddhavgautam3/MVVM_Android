package com.example.mvvm.datalayer.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ApplicationModuleTest {

    // Use HiltAndroidRule to set up Hilt for the test
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var applicationContext: Context

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
    fun provideApplicationContext() {
        assert(applicationContext is Application)
    }
}
