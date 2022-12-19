package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.auth.*
import com.heechan.membeder.model.service.AuthService
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.util.exception.TokenNullException
import retrofit2.Response

class AuthRepositoryImpl : AuthRepository {
    private val authService = RetrofitClient.getRetrofit().create(AuthService::class.java)

    override suspend fun getLoginUser(token: String?): Response<LoginUser> {
        if(token == null){
            throw TokenNullException()
        }

        val result = authService.getLoginUser(token = token)

        return result
    }

    override suspend fun getUserData(userId: String): Response<LoginUser> {
        val result = authService.getUserData(userId)

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

    override suspend fun googleLoginCallBack(googleLoginReq: GoogleLoginReq): Response<GoogleLoginRes> {
        val result = authService.googleCallback(googleLoginReq)

        return result
    }
}