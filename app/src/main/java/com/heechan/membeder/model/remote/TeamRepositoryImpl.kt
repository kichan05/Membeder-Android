package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.TeamService
import retrofit2.Response

class TeamRepositoryImpl : TeamRepository{
    private val service = RetrofitClient.getRetrofit().create(TeamService::class.java)

    override suspend fun createTeam(token: String, teamData: CreateTeamReq): Response<Team> {
        val result = service.createTeam(req = teamData, token = token)

        return result
    }

    override suspend fun getTeamInfo(token: String, id: String): Response<Team> {
        val result = service.getTeamInfo(id = id, token = token)

        return result
    }

    override suspend fun deleteTeam(token: String, id: String) {
        service.deleteTeam(id = id, token = token)
    }
}