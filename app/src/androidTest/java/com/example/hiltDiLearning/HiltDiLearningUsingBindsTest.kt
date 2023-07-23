package com.example.hiltDiLearning

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@LargeTest
@HiltAndroidTest
class HiltDiLearningUsingBindsTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var someConsumerClass: SomeConsumerClass

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testSomeComponents() {
        someConsumerClass.run()
    }

}