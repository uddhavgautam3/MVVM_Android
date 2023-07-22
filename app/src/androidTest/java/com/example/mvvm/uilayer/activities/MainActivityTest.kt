package com.example.mvvm.uilayer.activities

import androidx.test.core.app.launchActivity
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mvvm.R
import com.example.mvvm.uilayer.ui.activities.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class MainActivityTest {

    // Use ActivityScenarioRule to launch MainActivity for testing
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    // Enable Hilt for the test class using HiltAndroidRule
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        // Initialize Hilt for the test
        hiltRule.inject()
    }

    @Test
    fun testUserFragmentDisplayed() {
        launchActivity<MainActivity>()
    }

    @Test
    fun testButtonClicked() {
        onView(withId(R.id.userIdTv)).check(matches(withText("Users Info")))
        onView(withId(R.id.getUserByIdButton)).perform(click())
        onView(withId(R.id.getAllUserButton)).perform(click())

    }

    @Test
    fun testEditTextInput() {
        onView(withId(R.id.userIdField)).perform(typeText("123"))
        onView(withId(R.id.userIdField)).perform(closeSoftKeyboard())
    }

}