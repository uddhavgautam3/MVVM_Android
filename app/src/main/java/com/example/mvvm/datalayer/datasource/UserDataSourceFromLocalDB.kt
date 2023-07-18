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
        return MyResult.success(UserEntity(id, "From Local DB"))
    }

    override suspend fun getAllUsers(): MyResult<List<UserEntity>> {
        val userList: MyResult<List<UserEntity>> = MyResult.success(userDao.getAllUsers())
        return userList
    }

}
