package com.heechan.membeder.model.data.auth

data class GoogleLoginResponse(
    val aud: String,
    val azp: String,
    val email: String,
    val email_verified: Boolean,
    val exp: Int,
    val given_name: String,
    val iat: Int,
    val iss: String,
    val locale: String,
    val name: String,
    val picture: String,
    val registered: Boolean
)