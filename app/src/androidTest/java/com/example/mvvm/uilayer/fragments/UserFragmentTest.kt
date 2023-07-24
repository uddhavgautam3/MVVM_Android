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
        //launchFragmentInFragmentContainer is in FragmentScenario, which requires the fragments'
        // activity should have @AndroidEntryPoint for the activity. Since FragmentScenario internally
        // uses EmptyFragmentActivity, which is not ours, and this has no @AndroidEntryPoint, hence
        // keeps giving error like "Must be attached to Activity with @AndroidEntryPoint. To hack this
        // hilt's fragment testing problem and as recommended by Google, we created debug sourceSet, and
        // then there we created an empty activity HiltActivity, and HiltExt.kt, where we defined below function
        launchFragmentInHiltContainer<UserFragment> {}
    }

}