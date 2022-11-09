package com.heechan.membeder.model.data.schedule

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.heechan.membeder.model.data.auth.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule(
    val id : String,        // 일정 id
    val name : String,      // 일정 이름
    val description : String,   // 일정 설명
    val complete : Boolean, // 일정 완료 여부
    val permission : List<User>, //일정 수정 권한
    @SerializedName("deadline")
    val deadLine : String,  // 일정 마감일
) : Parcelable