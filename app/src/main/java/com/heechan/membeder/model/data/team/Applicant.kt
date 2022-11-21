package com.heechan.membeder.model.data.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Applicant(
    val developer : Int,
    val designer : Int,
    val director : Int
) : Parcelable
