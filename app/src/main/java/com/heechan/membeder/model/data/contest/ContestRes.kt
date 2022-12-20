package com.heechan.membeder.model.data.contest

data class ContestRes (
    val id : String,
    val name : String,
    val host : String,
    val target : List<String>,
    val receipt : String,
    val judge : String,
    val content : String,
    val poster : String,
    val award : String
    )