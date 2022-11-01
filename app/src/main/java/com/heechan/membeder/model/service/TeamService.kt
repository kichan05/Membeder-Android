package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import retrofit2.Response
import retrofit2.http.*

interface TeamService {
    @POST("/team")
    suspend fun createTeam(
        @Header("Authentication") token : String,
        @Body req : CreateTeamReq
    ) : Response<Team>

    @GET("/team/{id}")
    suspend fun getTeamInfo(
        @Header("Authentication") token : String,
        @Path("id") id:String
    ): Response<Team>

    @DELETE("/team/{id}")
    suspend fun deleteTeam(
        @Header("Authentication") token : String,
        @Path("id") id:String
    )
}