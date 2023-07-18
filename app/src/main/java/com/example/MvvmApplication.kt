package com.example

import android.app.Application
import com.example.mvvm.datalayer.db.UserDatabase
import com.example.mvvm.datalayer.model.UserEntity
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MvvmApplication : Application() {
    @Inject
    lateinit var userDatabase: UserDatabase

    @OptIn(DelicateCoroutinesApi::class)
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

        //val database = UserDatabase.getDatabase(this)

        GlobalScope.launch(Dispatchers.IO) {
            userDatabase.userDao().insertUsers(userList.map { UserEntity(it.id, it.name) })
        }
    }
}
