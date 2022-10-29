package com.heechan.membeder.model.data.auth

import androidx.datastore.preferences.protobuf.TypeOrBuilder

data class SignUpReq(
    val type : String,
    val name: String,
    val nickname: String,
    val birth : String,
    val picture : String,
    val email: String,
    val password: String,
    val profession: String,
    val career: Int,
    val website: String,
    val introduce: String,
    val stack: String,
    val department: String,
)