package com.heechan.membeder.model.remote

import com.google.firebase.firestore.Query
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.model.data.chat.ChatRoom
import com.heechan.membeder.model.data.chat.ChatRoomReq
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.util.State
import retrofit2.Response

interface ChatRepository {
    fun getChatList(roomId : String): Query
    suspend fun sendMessage(chatData : Chat) : State
    suspend fun addRoom(roomReq: ChatRoomReq, token : String? = SingletonObject.token.value) : Response<ChatRoom>
}