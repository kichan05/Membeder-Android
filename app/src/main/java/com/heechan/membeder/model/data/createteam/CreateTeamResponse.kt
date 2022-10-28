package com.heechan.membeder.model.data.createteam

import com.google.gson.annotations.SerializedName
import com.heechan.membeder.model.data.auth.User

data class CreateTeamResponse(
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("private")
    val private : Boolean,
    @SerializedName("image")
    val image : String,

    @SerializedName("applicant")
    val applicant : Applicant,

    @SerializedName("id")
    val id:String,

    @SerializedName("owner")
    val owner:User,

    @SerializedName("member")
    val member : ArrayList<String>,

    @SerializedName("created")
    val created : String,
    @SerializedName("updated")
    val updated : String
)
