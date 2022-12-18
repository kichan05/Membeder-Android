package com.heechan.membeder.model.remote

import com.google.firebase.firestore.Query
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.util.State

interface ChatRepository {
    fun getChatList(roomId : String): Query
    suspend fun sendMessage(chatData : Chat) : State
}