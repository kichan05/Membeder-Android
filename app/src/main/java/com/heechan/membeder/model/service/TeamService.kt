package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.data.team.TeamListRes
import retrofit2.Response
import retrofit2.http.*

interface TeamService {
    @POST("/team")
    suspend fun createTeam(
        @Body req : CreateTeamReq,
        @Header("Authentication") token : String,
    ) : Response<Team>

    @GET("/team/{id}")
    suspend fun getTeamInfo(
        @Path("id") id:String,
        @Header("Authentication") token : String,
    ): Response<Team>

    @DELETE("/team/{id}")
    suspend fun deleteTeam(
        @Path("id") id:String,
        @Header("Authentication") token : String,
    )

    @GET("/team/{team_id}/{user_id}")
    suspend fun getMember(
        @Path("team_id") team_id:String,
        @Path("user_id") user_id:String,
        @Header("Authentication") token : String,
    ): Response<Team>

    @GET("/team/{team_id}/{user_id}")
    suspend fun deleteMember(
        @Path("team_id") team_id:String,
        @Path("user_id") user_id:String,
        @Header("Authentication") token : String,
    ): Response<Team>

    @GET("/team")
    suspend fun getTeamList(
        @Header("Authentication") token : String,
    ): Response<TeamListRes>
}