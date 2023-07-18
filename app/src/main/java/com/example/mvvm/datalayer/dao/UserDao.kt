package com.example.mvvm.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.datalayer.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(userEntities: List<UserEntity>)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: Int): UserEntity?

    @Query("DELETE FROM users")
    fun deleteAllUsers()

    @Query("DELETE FROM users WHERE id = :userId")
    fun deleteUser(userId: Int)

}