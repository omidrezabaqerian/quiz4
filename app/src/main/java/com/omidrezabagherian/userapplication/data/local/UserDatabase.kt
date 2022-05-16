package com.omidrezabagherian.userapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDao::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}