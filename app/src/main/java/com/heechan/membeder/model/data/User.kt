package com.heechan.membeder.model.data

import com.squareup.moshi.Json

data class User(
    @Json(name = "type")
    val accountType : String,   // 로그인 종류, Google login or Email
    val name : String,          // 사용자 이름
    val nickname: String,       // 사용자 닉네임
    val email : String,         // 이메일
    val password : String = "응애", // 비밀번호, 제거 예정
    val profession : String,    // 직종
    val career : Int,           // 경력
    val website: String,        // 웹 사이트 링크
    val introduce : String,     // 소개 문구
    val stack : String,         // 기술 스택
    val department : String     // 분야
)
