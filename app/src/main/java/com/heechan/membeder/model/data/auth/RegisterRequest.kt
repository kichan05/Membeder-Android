package com.heechan.membeder.model.data.auth

import androidx.datastore.preferences.protobuf.TypeOrBuilder

data class RegisterRequest(
    val type : String,
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