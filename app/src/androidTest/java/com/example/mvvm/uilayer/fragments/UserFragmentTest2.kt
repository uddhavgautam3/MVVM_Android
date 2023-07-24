package com.example.mvvm.uilayer.fragments

import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mvvm.uilayer.ui.activities.UserActivity
import com.example.mvvm.uilayer.ui.fragments.UserFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * This is second way to test fragment. Here, we first launch activity that started the fragment,
 * and then use that to manually launch the fragment.
 */
@HiltAndroidTest
class UserFragmentTest2 {

    // Enable Hilt for the test class using HiltAndroidRule
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // Use ActivityScenarioRule to launch UserActivity for testing
    @get:Rule
    var activityRule: ActivityScenarioRule<UserActivity> =
        ActivityScenarioRule(UserActivity::class.java)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragmentInHiltContainer() {
        val activityScenario: ActivityScenario<UserActivity> = launchActivity()
        activityScenario.onActivity {
                val userFragment: Fragment = it.supportFragmentManager.fragmentFactory.instantiate(
                    Preconditions.checkNotNull(UserFragment::class.java.classLoader),
                    UserFragment::class.java.name
                )

            it.supportFragmentManager.beginTransaction()
                .add(android.R.id.content, userFragment, "")
                .commitNow()

        }
    }

}