package com.heechan.membeder.model.data.auth

data class LoginResponse(
    val user : User,
    val accessToken : String,
)
