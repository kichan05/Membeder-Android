package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.contest.ContestRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ContestService {
    @GET("/contest")
    suspend fun getContestList(
        @Query("page") page : Int,
        @Query("count") count : Int,
        @Header("Authentication") token : String,
    ): Response<List<ContestRes>>
}