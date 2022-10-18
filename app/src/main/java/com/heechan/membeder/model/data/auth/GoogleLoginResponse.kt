package com.heechan.membeder.model.data.auth

data class GoogleLoginResponse(
    val accessToken: String,
    val email: String,
    val email_verified: Boolean,
    val family_name: String,
    val given_name: String,
    val locale: String,
    val name: String,
    val picture: String,
    val registered: Boolean
)