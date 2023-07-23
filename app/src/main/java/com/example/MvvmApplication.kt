package com.example

import android.app.Application
import com.example.mvvm.datalayer.db.UserDatabase
import com.example.mvvm.datalayer.model.UserEntity
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/*
    To improve the MvvmApplication class:

    1. Avoid using GlobalScope for coroutines in the Application class.
    2. LifecycleScope is not directly available in the Application class
       as the Application itself doesn't have a lifecycle.

    Why not use GlobalScope?
    - GlobalScope creates coroutines that live throughout the entire application's lifecycle.
    - Coroutines launched with GlobalScope won't be automatically canceled when not needed,
      potentially causing memory leaks and degraded performance.
    - It lacks control over coroutine lifecycles, making resource management challenging.

    Instead, prefer structured concurrency:
    - Create custom CoroutineScopes tied to appropriate components' lifecycles:
      - Use lifecycleScope in activities and fragments.
      - Use viewModelScope in ViewModels.
      - Use a custom CoroutineScope for background tasks in the Application class.

    This ensures proper coroutine management, cancellation, and resource handling.
 */
@HiltAndroidApp
class MvvmApplication : Application(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    @Inject
    lateinit var userDatabase: UserDatabase

    override fun onCreate() {
        super.onCreate()

        val jsonData = """
            [
                {
                    "id": 1,
                    "name": "Uddhav Gautam"
                },
                {
                    "id": 2,
                    "name": "Roshani Dahal"
                },
                {
                    "id": 3,
                    "name": "Rose Gautam (The Future US President)"
                }
            ]
        """.trimIndent()

        val userList = Gson().fromJson(jsonData, Array<UserEntity>::class.java)

        try {
            launch {
                insertUsersToDatabase(userList)
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e("Error inserting users into the database: %s", e.message)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        // Cancel the CoroutineScope to avoid leaks
        cancel()
    }

    private fun insertUsersToDatabase(userList: Array<UserEntity>) {
        userDatabase.userDao().insertUsers(userList.toList())
    }

    companion object {
        private const val TAG = "MvvmApplication"
    }
}
