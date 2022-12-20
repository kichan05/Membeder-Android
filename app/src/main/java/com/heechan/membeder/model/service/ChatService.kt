package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.chat.ChatRoom
import com.heechan.membeder.model.data.chat.ChatRoomReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService {
    @GET("/chat/{room_id}")
    suspend fun getChtRoom(
        @Path("room_id") roomId: String,
        @Header("Authentication") token: String,
    ): Response<ChatRoom>

    @POST("/chat")
    suspend fun addChatRoom(
        @Body body: ChatRoomReq,
        @Header("Authentication") token: String,
    ): Response<ChatRoom>

    @DELETE("/chat/{room_id}")
    suspend fun deleteChatRoom(
        @Path("room_id") roomId: String,
        @Header("Authentication") token: String,
    )

    @POST("/chat/{room_id}/{user_id}")
    suspend fun addChatRoomMember(
        @Path("room_id") roomId: String,
        @Path("user_id") userId: String,
        @Header("Authentication") token: String,
    ): Response<ChatRoom>

    @DELETE("/chat/{room_id}/{user_id}")
    suspend fun deleteChatRoomMember(
        @Path("room_id") roomId: String,
        @Path("user_id") userId: String,
        @Header("Authentication") token: String,
    ): Response<ChatRoom>
}