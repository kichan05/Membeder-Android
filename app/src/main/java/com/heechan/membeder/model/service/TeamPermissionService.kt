package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.team.TeamPermissionRes
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TeamPermissionService {
    @POST("/team/permission/{team_id}/{user_id}")
    suspend fun addPermission(
        @Path("team_id") team_id : String,
        @Path("user_id") user_id : String,
        @Header("Authentication") token : String
    ): Response<TeamPermissionRes>

    @DELETE("/team/permission/{team_id}/{user_id}")
    suspend fun deletePermission(
        @Path("team_id") team_id : String,
        @Path("user_id") user_id : String,
        @Header("Authentication") token : String
    ): Response<TeamPermissionRes>
}