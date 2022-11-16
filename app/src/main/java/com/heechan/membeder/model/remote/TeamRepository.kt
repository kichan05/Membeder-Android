package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.data.team.TeamListRes
import com.heechan.membeder.model.data.team.TeamRes
import com.heechan.membeder.util.State
import retrofit2.Response

interface TeamRepository {
    suspend fun createTeam(teamData : CreateTeamReq, token : String? = SingletonObject.token.value) : Response<TeamRes>
    suspend fun getTeamInfo(id:String, token : String? = SingletonObject.token.value) : Response<TeamRes>
    suspend fun deleteTeam(id:String, token : String? = SingletonObject.token.value)
    suspend fun getTeamList(token : String? = SingletonObject.token.value) : Response<TeamListRes>

    suspend fun joinRequest(team_id: String, token: String? = SingletonObject.token.value): Response<TeamRes>
    suspend fun addMember(team_id: String, user_id: String, token: String? = SingletonObject.token.value): Response<TeamRes>
    suspend fun refusalJoinRequest(team_id: String, user_id: String, token: String? = SingletonObject.token.value): Response<TeamRes>
}