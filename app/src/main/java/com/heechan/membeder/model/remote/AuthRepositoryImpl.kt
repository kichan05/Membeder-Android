package com.heechan.membeder.model.remote

import com.heechan.membeder.BuildConfig
import com.heechan.membeder.model.data.auth.*
import com.heechan.membeder.model.service.AuthService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.CookieManager

class AuthRepositoryImpl : AuthRepository {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .cookieJar(JavaNetCookieJar(CookieManager()))
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
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