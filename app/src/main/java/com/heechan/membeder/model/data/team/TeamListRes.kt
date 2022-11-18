package com.heechan.membeder.model.data.team

import com.squareup.moshi.Json

data class TeamListRes(
    @Json(name="team")
    val teamList: List<Team>
)
