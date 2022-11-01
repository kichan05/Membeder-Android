package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.auth.*
import com.heechan.membeder.model.service.AuthService
import com.heechan.membeder.model.service.RetrofitClient
import retrofit2.Response

class AuthRepositoryImpl : AuthRepository {
    private val authService = RetrofitClient.getRetrofit().create(AuthService::class.java)

    override suspend fun getLoginUser(token : String): Response<SignUpRes> {
        val result = authService.getLoginUser(token)

        return result
    }

    override suspend fun login(loginReq: LoginReq): Response<LoginRes> {
        val result = authService.login(loginReq)

        return result
    }

    override suspend fun signUp(userData: SignUpReq): Response<SignUpRes> {
        val result = authService.signUp(userData)

        return result
    }
}