package com.heechan.membeder.model.remote

import android.util.Log
import com.heechan.membeder.model.data.User
import com.heechan.membeder.model.service.AuthService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        .baseUrl(BASE_URL)
        .client(clientBuilder.build())
        .build()

    private val authService = retrofit.create(AuthService::class.java)

    override suspend fun signUp(userData: User): Int {
        val result = authService.signUp(userData)
        Log.d("registerLog", result.toString())

        return 1
    }


    companion object {
        const val BASE_URL = "http://172.30.1.49:3000/"
    }
}