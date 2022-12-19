package com.heechan.membeder.model.data.chat

import android.util.Log
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
        Log.d("[ChatType]", "${message}, ${fromUserId}, ${SingletonObject.userData.value!!.id}")
        return fromUserId == SingletonObject.userData.value!!.id
    }
}