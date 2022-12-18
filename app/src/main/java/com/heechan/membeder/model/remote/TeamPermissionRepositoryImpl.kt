package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.data.team.TeamPermissionRes
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.TeamPermissionService
import com.heechan.membeder.util.exception.TokenNullException
import retrofit2.Response
import retrofit2.create

class TeamPermissionRepositoryImpl : TeamPermissionRepository{
    private val service = RetrofitClient.getRetrofit().create(TeamPermissionService::class.java)
    override suspend fun addPermission(teamData: String, userData: String, token: String?): Response<TeamPermissionRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.addPermission(team_id = teamData, user_id = userData, token = token)
        return result
    }

    override suspend fun deletePermission(teamData: String, userData: String, token: String?): Response<TeamPermissionRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.deletePermission(team_id = teamData, user_id = userData, token = token)
        return result
    }

}