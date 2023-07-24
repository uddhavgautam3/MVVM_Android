package com.example.mvvm.uilayer.fragments

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.mvvm.HiltTestActivity

//reified means the type information will be available during run-time. Type erasure won't do anything here
//Without reified, You won't have access to reflection capabilities for the generic type T at runtime.
// For example, you won't be able to use reflection to get the class name (T::class.java.name)
// or access other properties or methods specific to the actual type of T.

//inline means function can see it's outside, it acts like a closure. In other words,
// The function is an inline function, which means the code inside the function is directly inserted
// into the calling code during compilation, improving performance.

//When you see T.() -> Unit, this is lambda function representation

//The function launches an ActivityScenario (a testing utility for launching activities in Android) with a special test activity called HiltTestActivity,
// which is used for testing fragments with Hilt support.

//The function creates an Intent that identifies the HiltTestActivity as the main activity for the test.
inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    fragmentFactory: FragmentFactory? = null,
    crossinline action: T.() -> Unit = {}
) {
    val mainActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltTestActivity::class.java
        )
    )

    ActivityScenario.launch<HiltTestActivity>(mainActivityIntent).onActivity { activity ->
        fragmentFactory?.let {
            activity.supportFragmentManager.fragmentFactory = it
        }
        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        )
        fragment.arguments = fragmentArgs

        activity.supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        (fragment as T).action()
    }

}