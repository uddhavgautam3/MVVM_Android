package com.example.mvvm.datalayer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm.datalayer.dao.UserDao
import com.example.mvvm.datalayer.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}



