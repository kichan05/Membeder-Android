package com.heechan.membeder.model.remote

import com.google.firebase.firestore.Query

interface ChatRepository {
    fun getChatList(roomId : String): Query
}