package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import retrofit2.Response

interface TeamRepository {
    suspend fun createTeam(teamData : CreateTeamReq) : Response<Team>
    suspend fun getTeamInfo(id:String) : Response<Team>
    suspend fun deleteTeam(id:String)
}