package com.heechan.membeder.model.data.chat

import com.heechan.membeder.ui.SingletonObject
import java.util.Date

data class Chat(
    val id : String = "",
    val message : String = "",
    val fromUserId : String = "",
    val toRoomId : String = "",
    val timestamp : Date = Date(),
)  {
    fun isMyChat() : Boolean {
        return fromUserId == (SingletonObject.userData.value?.id ?: false)
    }
}