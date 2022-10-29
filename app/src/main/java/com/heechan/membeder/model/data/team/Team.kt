package com.heechan.membeder.model.data.team

import com.google.gson.annotations.SerializedName
import com.heechan.membeder.model.data.auth.User

data class Team(
    val name : String,
    val description : String,
    val private : Boolean,
    val image : String,

    val applicant : Applicant,
    val id:String,

    val owner:User,

    val member : ArrayList<User>,

    val created : String,
    val updated : String
)
