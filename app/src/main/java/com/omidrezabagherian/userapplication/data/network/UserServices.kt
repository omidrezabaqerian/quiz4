package com.omidrezabagherian.userapplication.data.network

import com.omidrezabagherian.userapplication.model.CreateUser
import com.omidrezabagherian.userapplication.model.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserServices {
    @POST("users")
    suspend fun createUser(@Body body: CreateUser): Response<String>

    @Multipart
    @POST("users/{userId}/image")
    suspend fun uploadImageUser(
        @Path("userId") id: String,
        @Part image: MultipartBody.Part
    ): Response<Any>

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{userID}")
    suspend fun getInformationUser(
        @Path("userId") id: String
    ): Response<User>
}