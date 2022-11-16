package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.schedule.ScheduleAddReq
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.ScheduleService
import com.heechan.membeder.util.exception.TokenNullException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class ScheduleRepositoryImpl : ScheduleRepository {
    private val service = RetrofitClient.getRetrofit().create(ScheduleService::class.java)

    override suspend fun addSchedule(teamId: String, scheduleData: ScheduleAddReq, token : String?): Response<Schedule> {
        if(token == null)
            throw TokenNullException()

        val result = service.addSchedule(teamId = teamId, body = scheduleData, token = token)

        return result
    }
}