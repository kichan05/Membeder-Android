package com.heechan.membeder.model.data.chat

import com.heechan.membeder.model.data.auth.User

data class Chat(
    val user : User,
    val message : String
)