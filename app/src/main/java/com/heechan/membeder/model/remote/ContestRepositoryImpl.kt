package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.contest.ContestRes
import com.heechan.membeder.model.service.ContestService
import com.heechan.membeder.model.service.RetrofitClient
import com.heechan.membeder.model.service.TeamService
import com.heechan.membeder.util.exception.TokenNullException
import retrofit2.Response

class ContestRepositoryImpl : ContestRepository{
    private val service = RetrofitClient.getRetrofit().create(ContestService::class.java)

    override suspend fun getContestList(token: String?): Response<List<ContestRes>> {
        if (token == null)
            throw TokenNullException()

        val result = service.getContestList(page = 1, count = 50, token = token)
        return result
    }

}