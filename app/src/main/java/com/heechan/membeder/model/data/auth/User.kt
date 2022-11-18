package com.heechan.membeder.model.data.auth

import com.squareup.moshi.Json
import android.os.Parcelable
import com.heechan.membeder.model.data.team.Team
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id : String,
    @Json(name="type")
    val accountType : String,   // 로그인 종류, Google login or Email
    val name : String,          // 사용자 이름
    val nickname: String,       // 사용자 닉네임
    val birth : String,         // 생일
    @Json(name="picture")
    val profileImg : String,       // 프로필 이미지
    val email : String,         // 이메일
    val profession : String,    // 직종
    val career : Int,           // 경력
    val website: String,        // 웹 사이트 링크
    val introduce : String,     // 소개 문구
    val stack : String,         // 기술 스택
    val department : String,     // 분야
    @Json(name="team")
    val teamList : List<Team> = listOf() // 팀 리스트
) : Parcelable