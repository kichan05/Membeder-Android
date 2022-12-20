package com.heechan.membeder.model.data.team

import com.google.gson.annotations.SerializedName

data class CreateTeamReq(
    val name : String,
    val description : String,
    val private : Boolean,
    val image : String,
    val applicant : Applicant
)
