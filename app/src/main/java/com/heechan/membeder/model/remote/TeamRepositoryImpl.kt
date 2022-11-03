package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.TeamService
import com.heechan.membeder.util.exception.TokenNullException
import retrofit2.Response

class TeamRepositoryImpl : TeamRepository{
    private val service = RetrofitClient.getRetrofit().create(TeamService::class.java)

    override suspend fun createTeam(teamData: CreateTeamReq, token: String?): Response<Team> {
        if(token == null)
            throw TokenNullException()

        val result = service.createTeam(req = teamData, token = token)

        return result
    }

    override suspend fun getTeamInfo(id: String, token: String?): Response<Team> {
        if(token == null)
            throw TokenNullException()

        val result = service.getTeamInfo(id = id, token = token)

        return result
    }

    override suspend fun deleteTeam(id: String, token: String?) {
        if(token == null)
            throw TokenNullException()

        service.deleteTeam(id = id, token = token)
    }
}