package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.createteam.CreateTeamRequest
import com.heechan.membeder.model.data.createteam.CreateTeamResponse
import retrofit2.Response

interface CreateTeamRepository {
    suspend fun createTeam(userData : CreateTeamRequest) : Response<CreateTeamResponse>
}