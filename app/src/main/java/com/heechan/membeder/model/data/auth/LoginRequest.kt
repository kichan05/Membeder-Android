package com.heechan.membeder.model.data.auth

data class LoginRequest(
    val email : String,
    val password : String,
)