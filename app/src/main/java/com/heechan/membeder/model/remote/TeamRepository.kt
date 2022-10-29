package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.team.TeamRequest
import com.heechan.membeder.model.data.team.TeamResponse
import retrofit2.Response

interface TeamRepository {
    suspend fun createTeam(teamData : TeamRequest) : Response<TeamResponse>
    suspend fun getTeamInfo(id:String) : Response<TeamResponse>
    suspend fun deleteTeam(id:String)
}