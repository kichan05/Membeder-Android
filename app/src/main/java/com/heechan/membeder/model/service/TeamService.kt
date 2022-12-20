package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.schedule.ScheduleAddReq
import com.heechan.membeder.model.data.schedule.TodoEditReq
import com.heechan.membeder.model.data.team.*
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

    @PATCH("/team/{id}")
    suspend fun EditTeam(
        @Path("id") id:String,
        @Body req : EditTeamReq,
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

    @POST("/team/join/{team_id}")
    suspend fun joinRequest(
        @Path("team_id") team_id:String,
        @Header("Authentication") token : String,
    ): Response<TeamRes>

    @POST("/team/{team_id}/{user_id}")
    suspend fun addMember(
        @Path("team_id") team_id:String,
        @Path("user_id") user_id:String,
        @Header("Authentication") token : String,
    ): Response<TeamRes>

    @DELETE("/team/join/{team_id}/{user_id}")
    suspend fun refusalJoinRequest(
        @Path("team_id") team_id:String,
        @Path("user_id") user_id:String,
        @Header("Authentication") token : String,
    ) : Response<TeamRes>


    @DELETE("/team/{team_id}/{user_id}")
    suspend fun BanMember(
        @Path("team_id") team_id:String,
        @Path("user_id") user_id:String,
        @Header("Authentication") token : String,
    ) : Response<TeamRes>

    @PATCH("/team/{team_id}/{schedule_id}")
    suspend fun TodoEdit(
        @Path("team_id") team_id:String,
        @Path("schedule_id") schedule_id:String,
        @Header("Authentication") token : String,
    ) : Response<TeamRes>

    @DELETE("/schedule/{team_id}/{schedule_id}")
    suspend fun TodoDelete(
        @Path("team_id") team_id:String,
        @Path("schedule_id") schedule_id:String,
        @Header("Authentication") token : String,
    )


    @PATCH("/schedule/{teamId}/{schedule_id}")
    suspend fun EditSchedule(
        @Path("teamId") team_id: String,
        @Path("schedule_id") schedule_id:String,
        @Body body : TodoEditReq,
        @Header("Authentication") token : String
    ) : Response<Schedule>

    @POST("/team/notice/{teamId}")
    suspend fun AddNotice(
        @Path("teamId") team_id: String,
        @Body req : EditNoticeReq,
        @Header("Authentication") token : String
    ) : Response<TeamRes>
}