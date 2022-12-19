package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @GET("/auth")
    suspend fun getLoginUser(
        @Header("Authentication") token : String
    ) : Response<LoginUser>

    @POST("/auth") //로그인
    suspend fun login(@Body body : LoginReq) : Response<LoginRes>

    @POST("/auth/signup") //회원가입
    suspend fun signUp(@Body body: SignUpReq) : Response<SignUpRes>

    @POST("/google")
    suspend fun googleCallback(@Body body : GoogleLoginReq) : Response<GoogleLoginRes>

    @GET("/user/{userId}")
    suspend fun getUserData(@Path("userId") userId : String) : Response<LoginUser>
}