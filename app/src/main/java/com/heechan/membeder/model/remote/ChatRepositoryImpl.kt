package com.heechan.membeder.model.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ChatRepositoryImpl : ChatRepository {
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getChatList(roomId: String): Query {
        return db.collection("chat")
            .whereEqualTo("toRoomId", roomId)
    }
}