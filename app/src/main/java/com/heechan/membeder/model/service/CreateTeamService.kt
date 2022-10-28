package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.createteam.CreateTeamRequest
import com.heechan.membeder.model.data.createteam.CreateTeamResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateTeamService {
    @POST("/team")
    suspend fun createTeam(@Body req : CreateTeamRequest) : Response<CreateTeamResponse>
}