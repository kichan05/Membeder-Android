package com.heechan.membeder.model.remote

import com.heechan.membeder.BuildConfig
import com.heechan.membeder.model.data.createteam.CreateTeamRequest
import com.heechan.membeder.model.data.createteam.CreateTeamResponse
import com.heechan.membeder.model.service.CreateTeamService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class CreateTeamRepositoryImpl : CreateTeamRepository{
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

    private val service = retrofit.create(CreateTeamService::class.java)

    override suspend fun createTeam(userData: CreateTeamRequest): Response<CreateTeamResponse> {
        val result = service.createTeam(userData)

        return result
    }


}