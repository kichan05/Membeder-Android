package com.heechan.membeder.model.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.util.State
import kotlinx.coroutines.tasks.await

class ChatRepositoryImpl : ChatRepository {
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getChatList(roomId: String): Query {
        return db.collection("chat")
//            .whereEqualTo("toRoomId", roomId)
            .orderBy("timestamp", Query.Direction.ASCENDING)
    }

    override suspend fun sendMessage(chatData: Chat) : State {
        var result : State = State.FAIL

        db.collection("chat").add(chatData).addOnSuccessListener {
            result = State.SUCCESS
        }.await()

        return result
    }
}