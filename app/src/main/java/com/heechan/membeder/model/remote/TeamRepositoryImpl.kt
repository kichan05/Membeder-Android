package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.TeamService
import retrofit2.Response

class TeamRepositoryImpl : TeamRepository{
    private val service = RetrofitClient.getRetrofit().create(TeamService::class.java)

    override suspend fun createTeam(token: String, teamData: CreateTeamReq): Response<Team> {
        val result = service.createTeam(token = token, req = teamData)

        return result
    }

    override suspend fun getTeamInfo(token: String, id: String): Response<Team> {
        val result = service.getTeamInfo(token = token, id = id)

        return result
    }

    override suspend fun deleteTeam(token: String, id: String) {
        service.deleteTeam(token = token, id = id)
    }


}