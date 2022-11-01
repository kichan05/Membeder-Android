package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import retrofit2.Response

interface TeamRepository {
    suspend fun createTeam(token : String, teamData : CreateTeamReq) : Response<Team>
    suspend fun getTeamInfo(token : String, id:String) : Response<Team>
    suspend fun deleteTeam(token : String, id:String)
}