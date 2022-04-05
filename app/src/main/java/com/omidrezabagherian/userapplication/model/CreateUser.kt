package com.omidrezabagherian.userapplication.model

data class CreateUser(
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String>
)
