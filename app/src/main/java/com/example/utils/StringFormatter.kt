package com.example.utils

import com.example.mvvm.datalayer.model.UserEntity

class StringFormatter {
    companion object {
        fun formatUserList(userEntityList: List<UserEntity?>?): String {
            return userEntityList?.joinToString("\n") { user ->
                user?.let { "${it.name}, ID: ${it.id}" }.toString()
            } ?: ""
        }
    }
}