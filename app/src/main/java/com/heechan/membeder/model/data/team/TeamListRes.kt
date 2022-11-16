package com.heechan.membeder.model.data.team

import com.google.gson.annotations.SerializedName

data class TeamListRes(
    @SerializedName("team")
    val teamList: List<Team>
)
