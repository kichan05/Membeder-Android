package com.heechan.membeder.model.data.team

import com.google.gson.annotations.SerializedName

data class TeamRequest(
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("private")
    val private : Boolean,
    @SerializedName("image")
    val image : String,

    @SerializedName("applicant")
    val applicant : Applicant
)
