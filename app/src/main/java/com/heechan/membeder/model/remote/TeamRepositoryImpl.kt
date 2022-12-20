package com.heechan.membeder.model.remote

import android.util.Log
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.schedule.ScheduleAddReq
import com.heechan.membeder.model.data.schedule.TodoEditReq
import com.heechan.membeder.model.data.team.*
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.TeamService
import com.heechan.membeder.util.State
import com.heechan.membeder.util.exception.PrivareFalseException
import com.heechan.membeder.util.exception.TokenNullException
import retrofit2.Response

class TeamRepositoryImpl : TeamRepository {
    private val service = RetrofitClient.getRetrofit().create(TeamService::class.java)

    override suspend fun createTeam(teamData: CreateTeamReq, token: String?): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.createTeam(req = teamData, token = token)

        return result
    }
    override suspend fun createTeam(id: String,teamData: EditTeamReq, token: String?): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()


        val result = service.EditTeam(id = id, req = teamData , token = token)

        return result
    }

    override suspend fun getTeamInfo(id: String, token: String?): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.getTeamInfo(id = id, token = token)

        return result
    }

    override suspend fun deleteTeam(id: String, token: String?) {
        if (token == null)
            throw TokenNullException()

        service.deleteTeam(id = id, token = token)
    }

    override suspend fun getTeamList(token: String?): Response<TeamListRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.getTeamList(token = token)
        return result
    }

    override suspend fun addMember(
        team_id: String,
        user_id: String,
        token: String?
    ): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()

        val result = service.addMember(team_id = team_id, user_id = user_id, token = token)
        return result
    }

    override suspend fun refusalJoinRequest(
        team_id: String,
        user_id: String,
        token: String?
    ): Response<TeamRes> {
        if(token == null)
            throw TokenNullException()

        val result = service.refusalJoinRequest(team_id = team_id, user_id = user_id, token = token)
        return result
    }

    override suspend fun BanMember(
        team_id: String,
        user_id: String,
        token: String?
    ): Response<TeamRes> {
        if(token == null)
            throw TokenNullException()

        val result = service.BanMember(team_id = team_id, user_id = user_id, token = token)
        return result
    }

    override suspend fun TodoEdit(team_id: String, schedule_id: String,token: String?): Response<TeamRes> {
        if(token == null)
            throw TokenNullException()

        val result = service.TodoEdit(team_id = team_id, schedule_id = schedule_id, token = token)
        return result
    }



    override suspend fun joinRequest(team_id: String, private:Boolean,token: String?): Response<TeamRes> {
        if (token == null)
            throw TokenNullException()
        if (private == false)
            throw PrivareFalseException()

        val result = service.joinRequest(team_id = team_id, token = token)

        return result
    }

    override suspend fun TodoDelete(team_id: String, schedule_id: String, token: String?) {
        if (token == null)
            throw TokenNullException()

        val result = service.TodoDelete(team_id = team_id, schedule_id= schedule_id, token = token)

        return result
    }


    override suspend fun EditSchedule(team_id: String, schedule_id:String ,scheduleData: TodoEditReq, token: String?): Response<Schedule> {
        if (token == null)
            throw TokenNullException()

        val result = service.EditSchedule(team_id = team_id, schedule_id = schedule_id, body= scheduleData, token = token)

        return result
    }


}