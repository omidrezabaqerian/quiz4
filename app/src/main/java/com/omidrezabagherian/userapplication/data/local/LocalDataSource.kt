package com.omidrezabagherian.userapplication.data.local

import com.omidrezabagherian.userapplication.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    fun getUserList(): Flow<List<User>> = userDao.getUserList()
}