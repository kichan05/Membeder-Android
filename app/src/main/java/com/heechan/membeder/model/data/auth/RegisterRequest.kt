package com.heechan.membeder.model.data.auth

data class RegisterRequest(
    val career: Int,
    val department: String,
    val email: String,
    val introduce: String,
    val name: String,
    val nickname: String,
    val password: String,
    val profession: String,
    val stack: String,
    val website: String
)