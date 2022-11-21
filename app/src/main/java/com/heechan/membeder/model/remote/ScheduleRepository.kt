package com.heechan.membeder.model.remote

import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.schedule.ScheduleAddReq
import retrofit2.Response

interface ScheduleRepository {
    suspend fun addSchedule(teamId: String, scheduleData: ScheduleAddReq, token : String? = SingletonObject.token.value): Response<Schedule>
}