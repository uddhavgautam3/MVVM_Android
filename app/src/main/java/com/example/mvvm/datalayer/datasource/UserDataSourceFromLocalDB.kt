package com.example.mvvm.datalayer.datasource

import com.example.mvvm.datalayer.dao.UserDao
import com.example.mvvm.datalayer.model.MyResult
import com.example.mvvm.datalayer.model.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSourceFromLocalDB @Inject constructor(
    private val userDao: UserDao
) : UserDataSource {
    override suspend fun getUser(id: Int): MyResult<UserEntity> {
        return try {
            val user = userDao.getUser(id)
            MyResult.success(user)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }

    override suspend fun getAllUsers(): MyResult<List<UserEntity>> {
        return try {
            val userList = userDao.getAllUsers()
            MyResult.success(userList)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }

}
