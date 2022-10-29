package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TeamService {
    @POST("/team")
    suspend fun createTeam(@Body req : CreateTeamReq) : Response<Team>

    @GET("/team/{id}")
    suspend fun getTeamInfo(@Path("id") id:String): Response<Team>

    @DELETE("/team/{id}")
    suspend fun deleteTeam(@Path("id") id:String)
}