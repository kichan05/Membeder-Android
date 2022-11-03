package com.heechan.membeder.model.data.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoogleLoginRes(
    val email: String,
    val email_verified: Boolean,
    val name: String,
    val given_name: String,
    val locale: String,
    val picture: String,
    val registered: Boolean,

    val accessToken : String
) : Parcelable