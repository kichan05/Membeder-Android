package com.heechan.membeder.model.data.auth

data class LoginRes(
    val user : User,
    val accessToken : String,
)
