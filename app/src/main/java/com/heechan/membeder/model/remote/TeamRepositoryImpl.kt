package com.heechan.membeder.model.remote

import com.heechan.membeder.BuildConfig
import com.heechan.membeder.model.data.team.TeamRequest
import com.heechan.membeder.model.data.team.TeamResponse
import com.heechan.membeder.model.service.TeamService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class TeamRepositoryImpl : TeamRepository{
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val clientBuilder = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(clientBuilder.build())
        .build()

    private val service = retrofit.create(TeamService::class.java)

    override suspend fun createTeam(teamData: TeamRequest): Response<TeamResponse> {
        val result = service.createTeam(teamData)

        return result
    }

    override suspend fun getTeamInfo(id: String): Response<TeamResponse> {
        val result = service.getTeamInfo(id)

        return result
    }

    override suspend fun deleteTeam(id: String) {
        service.deleteTeam(id)
    }


}