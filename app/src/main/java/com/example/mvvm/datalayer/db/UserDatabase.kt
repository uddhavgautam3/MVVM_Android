package com.example.mvvm.datalayer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.datalayer.dao.UserDao
import com.example.mvvm.datalayer.model.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    /*abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }*/

    abstract fun userDao(): UserDao

    @Module
    @InstallIn(SingletonComponent::class)
    object DatabaseModule {
        @Provides
        @Singleton
        fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
            return Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                "user_database"
            ).build()
        }

        @Provides
        fun provideUserDao(userDatabase: UserDatabase): UserDao {
            return userDatabase.userDao()
        }
    }
}



