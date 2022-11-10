package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.data.team.TeamListRes
import com.heechan.membeder.model.data.team.TeamRes
import retrofit2.Response
import retrofit2.http.*

interface TeamService {
    @GET("/team")
    suspend fun getTeamList(
        @Header("Authentication") token : String,
    ): Response<TeamListRes>

    @POST("/team")
    suspend fun createTeam(
        @Body req : CreateTeamReq,
        @Header("Authentication") token : String,
    ) : Response<TeamRes>

    @GET("/team/{id}")
    suspend fun getTeamInfo(
        @Path("id") id:String,
        @Header("Authentication") token : String,
    ): Response<TeamRes>

    @DELETE("/team/{id}")
    suspend fun deleteTeam(
        @Path("id") id:String,
        @Header("Authentication") token : String,
    )

    @PATCH("/team/{id}")
    suspend fun editTeam(
        @Path("id") id:String,
        @Body req : CreateTeamReq,
        @Header("Authentication") token : String,
    ): Response<TeamRes>

    @GET("/team/{team_id}/{user_id}")
    suspend fun getMember(
        @Path("team_id") team_id:String,
        @Path("user_id") user_id:String,
        @Header("Authentication") token : String,
    ): Response<Team>

    @POST("/team/{team_id}/{user_id}")
    suspend fun addMember(
        @Path("team_id") team_id:String,
        @Path("user_id") user_id:String,
        @Header("Authentication") token : String,
    ): Response<TeamRes>

//    @DELETE("/team/{team_id}/{user_id}")
//    suspend fun deleteMember(
//        @Path("team_id") team_id:String,
//        @Path("user_id") user_id:String,
//        @Header("Authentication") token : String,
//    )
}