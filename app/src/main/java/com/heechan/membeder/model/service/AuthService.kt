package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/signup")
    suspend fun signUp(@Body body: SignUpRequest) : Response<SignUpResponse>

    @POST("/auth")
    suspend fun login(@Body body : LoginRequest) : Response<LoginResponse>
}