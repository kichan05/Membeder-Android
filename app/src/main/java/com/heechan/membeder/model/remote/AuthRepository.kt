package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.auth.*
import retrofit2.Response

interface AuthRepository {
    suspend fun getLoginUser() : Response<SignUpResponse>
    suspend fun login(loginReq : LoginRequest) : Response<LoginResponse>
    suspend fun signUp(userData : SignUpRequest) : Response<SignUpResponse>
}