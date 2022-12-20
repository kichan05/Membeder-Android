package com.heechan.membeder.model.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.model.data.chat.ChatRoom
import com.heechan.membeder.model.data.chat.ChatRoomReq
import com.heechan.membeder.model.service.ChatService
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.util.State
import com.heechan.membeder.util.exception.TokenNullException
import kotlinx.coroutines.tasks.await
import retrofit2.Response

class ChatRepositoryImpl : ChatRepository {
    private val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val chatService = RetrofitClient.getRetrofit().create(ChatService::class.java)

    override fun getChatList(roomId: String): Query {
        return firestore.collection("chat")
//            .whereEqualTo("toRoomId", roomId)
            .orderBy("timestamp", Query.Direction.ASCENDING)
    }

    override suspend fun sendMessage(chatData: Chat) : State {
        var result : State = State.FAIL

        firestore.collection("chat").add(chatData).addOnSuccessListener {
            result = State.SUCCESS
        }.await()

        return result
    }

    override suspend fun addRoom(roomReq: ChatRoomReq, token: String?): Response<ChatRoom> {
        if(token == null)
            throw TokenNullException()

        val result = chatService.addChatRoom(roomReq, token)
        return result
    }
}