package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @GET("/auth")
    suspend fun getLoginUser() : Response<SignUpResponse>

    @POST("/auth") //로그인
    suspend fun login(@Body body : LoginRequest) : Response<LoginResponse>

    @POST("/auth/signup") //회원가입
    suspend fun signUp(@Body body: SignUpRequest) : Response<SignUpResponse>

    @POST("/google") //구글 로그인, 유저정보 가져오기
    suspend fun googleCallBack(@Body body : GoogleLoginRequest) : Response<GoogleLoginResponse>
}