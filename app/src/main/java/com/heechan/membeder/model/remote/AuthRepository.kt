package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.auth.LoginRequest
import com.heechan.membeder.model.data.auth.LoginResponse
import com.heechan.membeder.model.data.auth.RegisterRequest
import com.heechan.membeder.model.data.auth.User
import retrofit2.Response

interface AuthRepository {
    suspend fun signUp(userData : RegisterRequest) : Response<User>
    suspend fun login(loginReq : LoginRequest) : Response<LoginResponse>
}