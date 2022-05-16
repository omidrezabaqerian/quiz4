package com.omidrezabagherian.userapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val _id: String?,
    val firstName: String?,
    val lastName: String?,
    val nationalCode: String?,
    val image: String? = null
)