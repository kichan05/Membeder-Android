package com.heechan.membeder.model.remote

import com.heechan.membeder.BuildConfig
import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.TeamService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamRepositoryImpl : TeamRepository{
    private val service = RetrofitClient.retrofit.create(TeamService::class.java)

    override suspend fun createTeam(teamData: CreateTeamReq): Response<Team> {
        val result = service.createTeam(teamData)

        return result
    }

    override suspend fun getTeamInfo(id: String): Response<Team> {
        val result = service.getTeamInfo(id)

        return result
    }

    override suspend fun deleteTeam(id: String) {
        service.deleteTeam(id)
    }


}