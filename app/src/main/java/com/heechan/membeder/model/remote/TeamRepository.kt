package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.schedule.ScheduleAddReq
import com.heechan.membeder.model.data.schedule.TodoEditReq
import com.heechan.membeder.model.data.team.*
import com.heechan.membeder.ui.SingletonObject
import retrofit2.Response

interface TeamRepository {
    suspend fun createTeam(teamData : CreateTeamReq, token : String? = SingletonObject.token.value) : Response<TeamRes>
    suspend fun EditTeam(team_id: String, teamData : EditTeamReq, token : String? = SingletonObject.token.value) : Response<TeamRes>
    suspend fun getTeamInfo(id:String, token : String? = SingletonObject.token.value) : Response<TeamRes>
    suspend fun deleteTeam(id:String, token : String? = SingletonObject.token.value)
    suspend fun getTeamList(token : String? = SingletonObject.token.value) : Response<TeamListRes>

    suspend fun joinRequest(team_id: String, private : Boolean,token: String? = SingletonObject.token.value): Response<TeamRes>
    suspend fun addMember(team_id: String, user_id: String, token: String? = SingletonObject.token.value): Response<TeamRes>
    suspend fun refusalJoinRequest(team_id: String, user_id: String, token: String? = SingletonObject.token.value): Response<TeamRes>
    suspend fun BanMember(team_id: String, user_id: String, token: String? = SingletonObject.token.value): Response<TeamRes>
    suspend fun TodoEdit(team_id: String, schedule_id: String, token: String? = SingletonObject.token.value): Response<TeamRes>
    suspend fun TodoDelete(team_id: String, schedule_id: String, token: String? = SingletonObject.token.value)

    suspend fun EditSchedule(team_id: String, schedule_id: String, scheduleData: TodoEditReq, token: String? = SingletonObject.token.value): Response<Schedule>
    suspend fun AddNotice(team_id: String, EditNoticeReq: EditNoticeReq, token: String? = SingletonObject.token.value): Response<TeamRes>
}