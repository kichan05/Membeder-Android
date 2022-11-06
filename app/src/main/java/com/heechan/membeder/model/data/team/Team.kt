package com.heechan.membeder.model.data.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.heechan.membeder.model.data.auth.User
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable
