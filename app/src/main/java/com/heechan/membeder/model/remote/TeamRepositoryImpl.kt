package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.data.team.TeamListRes
import com.heechan.membeder.model.data.team.TeamRes
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.TeamService
import com.heechan.membeder.util.State
import com.heechan.membeder.util.exception.TokenNullException
import retrofit2.Response

class TeamRepositoryImpl : TeamRepository {
    private val service = RetrofitClient.getRetrofit().create(TeamService::class.java)

    override suspend fun createTeam(teamData: CreateTeamReq, token: String?): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.createTeam(req = teamData, token = token)

        return result
    }

    override suspend fun getTeamInfo(id: String, token: String?): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.getTeamInfo(id = id, token = token)

        return result
    }

    override suspend fun deleteTeam(id: String, token: String?) {
        if (token == null)
            throw TokenNullException()

        service.deleteTeam(id = id, token = token)
    }

    override suspend fun getTeamList(token: String?): Response<TeamListRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.getTeamList(token = token)
        return result
    }

    override suspend fun addMember(
        team_id: String,
        user_id: String,
        token: String?
    ): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.addMember(team_id = team_id, user_id = user_id, token = token)
        return result
    }

    override suspend fun refusalJoinRequest(
        team_id: String,
        user_id: String,
        token: String?
    ): Response<TeamRes> {
        if(token == null)
            throw TokenNullException()

        val result = service.refusalJoinRequest(team_id = team_id, user_id = user_id, token = token)
        return result
    }

    override suspend fun BanMember(
        team_id: String,
        user_id: String,
        token: String?
    ): Response<TeamRes> {
        if(token == null)
            throw TokenNullException()

        val result = service.BanMember(team_id = team_id, user_id = user_id, token = token)
        return result
    }

    override suspend fun joinRequest(team_id: String, token: String?): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.joinRequest(team_id = team_id, token = token)

        return result
    }


}