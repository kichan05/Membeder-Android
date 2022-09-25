package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/signup")
    suspend fun signUp(@Body body: User) : User
}