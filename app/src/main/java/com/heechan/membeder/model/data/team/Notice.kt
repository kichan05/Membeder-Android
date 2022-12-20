package com.heechan.membeder.model.data.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notice(
    val id : String,        // 일정 id
    val title : String,      // 일정 이름
    val context : String,   // 일정 설명
    val created : String,   // 일정 생성일
): Parcelable
