package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.contest.ContestRes
import com.heechan.membeder.ui.SingletonObject
import retrofit2.Response

interface ContestRepository {
    suspend fun getContestList(token : String? = SingletonObject.token.value) : Response<List<ContestRes>>
}