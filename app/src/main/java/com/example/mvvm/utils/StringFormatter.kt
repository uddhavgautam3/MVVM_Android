package com.example.mvvm.utils

import com.example.mvvm.model.User

class StringFormatter {
    companion object {
        fun formatUserList(userList: List<User?>?): String {
            val stringBuilder = StringBuilder()
            if (userList != null) {
                for (user in userList) {
                    user?.let {
                        stringBuilder.append("${user.name}, ID: ${user.id}\n")
                    }
                }
            }
            return stringBuilder.toString()
        }
    }
}