package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.data.team.TeamListRes
import retrofit2.Response

interface TeamRepository {
    suspend fun createTeam(teamData : CreateTeamReq, token : String? = SingletonObject.token) : Response<Team>
    suspend fun getTeamInfo(id:String, token : String? = SingletonObject.token) : Response<Team>
    suspend fun deleteTeam(id:String, token : String? = SingletonObject.token)
    suspend fun getMember(team_id: String, user_id: String, token: String?): Response<Team>
    suspend fun deleteMember(team_id: String, user_id: String, token: String?): Response<Team>
    suspend fun getTeamList(token : String? = SingletonObject.token) : Response<TeamListRes>
}