package com.heechan.membeder.model.data.chat

import android.os.Parcelable
import android.util.Log
import com.heechan.membeder.ui.SingletonObject
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Chat(
    val id : String = "",
    val message : String = "",
    val fromUserId : String = "",
    val toRoomId : String = "",
    val timestamp : Date = Date(),
) : Parcelable {
    fun isMyChat() : Boolean {
        Log.d("[ChatType]", "${message}, ${fromUserId}, ${SingletonObject.userData.value!!.id}")
        return fromUserId == SingletonObject.userData.value!!.id
    }
}