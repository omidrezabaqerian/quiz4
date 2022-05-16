package com.omidrezabagherian.userapplication.data

import com.omidrezabagherian.userapplication.data.local.LocalDataSource
import com.omidrezabagherian.userapplication.data.network.RemoteDataSource
import com.omidrezabagherian.userapplication.model.CreateUser
import com.omidrezabagherian.userapplication.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun createUser(user: CreateUser) = remoteDataSource.createUser(user)

    suspend fun uploadImageUser(id: String, image: MultipartBody.Part) =
        remoteDataSource.uploadImageUser(id, image)

    fun getUsers(): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading())

            try {
                val response = remoteDataSource.getUsers()
                emit(Resource.Success(response))

            } catch (e: Exception) {
                emit(Resource.Error(throwable = Throwable("Error")))
            }
        } as Flow<Resource<List<User>>>
    }

    suspend fun getInformationUser(id: String) = remoteDataSource.getInformationUser(id)

    suspend fun insertUser(user: User) = localDataSource.insertUser(user)

    suspend fun updateUser(user: User) = localDataSource.updateUser(user)

    suspend fun deleteUser(user: User) = localDataSource.deleteUser(user)

    fun getUserList(): Flow<List<User>> = localDataSource.getUserList()
}