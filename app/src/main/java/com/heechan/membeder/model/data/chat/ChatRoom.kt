package com.heechan.membeder.model.data.chat

import com.firebase.ui.auth.data.model.User

data class ChatRoom(
    val id : String,
    val name : String,
    val owner : User,
    val member : List<User>,
    val chat : List<Chat>,
    val created : String,
    val updated : String,
)