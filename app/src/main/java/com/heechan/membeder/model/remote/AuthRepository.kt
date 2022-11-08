package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.*
import retrofit2.Response

interface AuthRepository {
    suspend fun getLoginUser(token: String? = SingletonObject.token) : Response<LoginRes>
    suspend fun login(loginReq : LoginReq) : Response<LoginRes>
    suspend fun signUp(userData : SignUpReq) : Response<SignUpRes>
    suspend fun googleLoginCallBack(googleLoginReq: GoogleLoginReq) : Response<GoogleLoginRes>
}