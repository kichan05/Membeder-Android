package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.data.team.TeamPermissionRes
import com.heechan.membeder.ui.SingletonObject
import retrofit2.Response

interface TeamPermissionRepository {
    suspend fun addPermission(teamData : String, userData : String, token : String? = SingletonObject.token.value) : Response<TeamPermissionRes>
    suspend fun deletePermission(teamData : String, userData : String, token : String? = SingletonObject.token.value) : Response<TeamPermissionRes>
}