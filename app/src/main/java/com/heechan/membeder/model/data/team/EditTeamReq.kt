package com.heechan.membeder.model.data.team

data class EditTeamReq(
    val name : String,
    val description : String,
    val private : Boolean,
    val image : String,

    val applicant : Applicant
)

