package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.contest.ContestRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ContestService {
    @GET("/contest")
    suspend fun getContestList(
        @Header("Authentication") token : String,
    ): Response<List<ContestRes>>
}