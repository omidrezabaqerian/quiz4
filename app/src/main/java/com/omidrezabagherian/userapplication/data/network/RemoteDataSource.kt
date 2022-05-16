package com.omidrezabagherian.userapplication.data.network

import com.omidrezabagherian.userapplication.model.CreateUser
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val userServices: UserServices) {
    suspend fun createUser(body: CreateUser) = userServices.createUser(body)

    suspend fun uploadImageUser(id: String, image: MultipartBody.Part) =
        userServices.uploadImageUser(id, image)

    suspend fun getUsers() = userServices.getUsers()

    suspend fun getInformationUser(id: String) = userServices.getInformationUser(id)
}