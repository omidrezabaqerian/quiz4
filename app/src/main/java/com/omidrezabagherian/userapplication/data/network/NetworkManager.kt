package com.omidrezabagherian.userapplication.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val addHeaderClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Authorization", "Bearer bkjcsbcg687hwgjhgksc")
                .build()
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val userRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://51.195.19.222:3000/api/v1/")
        .client(addHeaderClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService: UserServices = userRetrofit.create(UserServices::class.java)
}