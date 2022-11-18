package com.heechan.membeder.model.data.schedule

import android.os.Parcelable
import com.heechan.membeder.model.data.auth.User
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule(
    val id : String,        // 일정 id
    val name : String,      // 일정 이름
    val description : String,   // 일정 설명
    val complete : Boolean, // 일정 완료 여부
    @Json(name="deadline")
    val deadLine : String,  // 일정 마감일

    val created : String,   // 일정 생성일
    val updated : String    // 일정 수정일
) : Parcelable