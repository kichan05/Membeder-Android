package com.heechan.membeder.model.data.createteam

import com.google.gson.annotations.SerializedName

data class Applicant(
    @SerializedName("developer")
    val developer : Int,
    @SerializedName("designer")
    val designer : Int,
    @SerializedName("director")
    val director : Int
)
