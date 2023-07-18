package com.example.utils

import com.example.mvvm.datalayer.model.UserEntity

class StringFormatter {
    companion object {
        fun formatUserList(userEntityList: List<UserEntity?>?): String {
            val stringBuilder = StringBuilder()
            if (userEntityList != null) {
                for (user in userEntityList) {
                    user?.let {
                        stringBuilder.append("${user.name}, ID: ${user.id}\n")
                    }
                }
            }
            return stringBuilder.toString()
        }
    }
}