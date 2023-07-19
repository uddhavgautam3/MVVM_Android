package com.example.mvvm.datalayer.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String
)