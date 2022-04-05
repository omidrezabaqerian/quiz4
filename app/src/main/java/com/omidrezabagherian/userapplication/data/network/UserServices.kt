package com.omidrezabagherian.userapplication.data.network

import com.omidrezabagherian.userapplication.model.CreateUser
import com.omidrezabagherian.userapplication.model.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserServices {
    @POST("users")
    fun createUser(@Body body: CreateUser): Call<String>

    @Multipart
    @POST("users/{userId}/image")
    fun uploadImageUser(
        @Path("userId") id: String,
        @Part image: MultipartBody.Part
    ): Call<Any>

    @GET("users")
    fun getUsers(): Call<List<User>>
}