package com.heechan.membeder.model.remote

import com.heechan.membeder.BuildConfig
import com.heechan.membeder.model.data.auth.RegisterRequest
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.service.AuthService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AuthRepositoryImpl : AuthRepository {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val clientBuilder = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(clientBuilder.build())
        .build()

    private val authService = retrofit.create(AuthService::class.java)

    override suspend fun signUp(userData: RegisterRequest): Response<User> {
        val result = authService.signUp(userData)

        return result
    }
}