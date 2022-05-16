package com.omidrezabagherian.userapplication.di

import android.content.Context
import androidx.room.Room
import com.omidrezabagherian.userapplication.data.local.UserDatabase
import com.omidrezabagherian.userapplication.data.network.UserServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader("Authorization", "Bearer bkjcsbcg687hwgjhgksc")
            .build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://51.195.19.222:3000/api/v1/")
            .addConverterFactory(gsonConverter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieAppService(retrofit: Retrofit): UserServices {
        return retrofit.create(UserServices::class.java)
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): UserDatabase = Room.databaseBuilder(
        context,
        UserDatabase::class.java, "UserDatabase"
    ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(db: UserDatabase) = db.userDao()
}