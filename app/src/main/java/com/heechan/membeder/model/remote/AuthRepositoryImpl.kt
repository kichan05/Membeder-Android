package com.heechan.membeder.model.remote

import com.heechan.membeder.BuildConfig
import com.heechan.membeder.model.data.auth.*
import com.heechan.membeder.model.service.AuthService
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager

class AuthRepositoryImpl : AuthRepository {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .cookieJar(JavaNetCookieJar(CookieManager()))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_BASE_URL)
        .build()

    private val authService = retrofit.create(AuthService::class.java)

    override suspend fun getLoginUser(): Response<SignUpResponse> {
        val result = authService.getLoginUser()

        return result
    }

    override suspend fun login(loginReq: LoginRequest): Response<LoginResponse> {
        val result = authService.login(loginReq)

        return result
    }

    override suspend fun signUp(userData: SignUpRequest): Response<SignUpResponse> {
        val result = authService.signUp(userData)

        return result
    }
}