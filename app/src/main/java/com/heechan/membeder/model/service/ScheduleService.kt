package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.schedule.ScheduleAddReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ScheduleService {
    @POST("/schedule/{teamId}")
    suspend fun addSchedule(
        @Path("teamId") teamId: String,
        @Body body : ScheduleAddReq,
        @Header("Authentication") token : String
    ) : Response<Schedule>
}