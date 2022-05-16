package com.omidrezabagherian.userapplication.data.local

import androidx.room.*
import com.omidrezabagherian.userapplication.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getUserList(): Flow<List<User>>

}