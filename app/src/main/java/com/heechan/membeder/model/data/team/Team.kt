package com.heechan.membeder.model.data.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.schedule.Schedule
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

    val member : List<User>,
    val permission : List<User>,
    val schedule : List<Schedule>,
    @SerializedName("join_request")
    val joinRequest : List<User>,

    val created : String,
    val updated : String
) : Parcelable {
    val isOwner : Boolean
        get() = owner.id == SingletonObject.userData.value!!.id
}