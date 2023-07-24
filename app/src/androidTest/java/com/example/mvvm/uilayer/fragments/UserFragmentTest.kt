package com.example.mvvm.uilayer.fragments

import com.example.mvvm.uilayer.ui.fragments.UserFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class UserFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragmentInHiltContainer() {
            launchFragmentInHiltContainer<UserFragment> {
        }
    }

}