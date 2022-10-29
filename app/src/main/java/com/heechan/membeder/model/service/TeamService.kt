package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.team.TeamRequest
import com.heechan.membeder.model.data.team.TeamResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TeamService {
    @POST("/team")
    suspend fun createTeam(@Body req : TeamRequest) : Response<TeamResponse>

    @GET("/team/{id}")
    suspend fun getTeamInfo(@Path("id") id:String): Response<TeamResponse>

    @DELETE("/team/{id}")
    suspend fun deleteTeam(@Path("id") id:String)
}