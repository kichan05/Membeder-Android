package com.heechan.membeder.model.remote

import android.net.Uri
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.model.data.auth.*
import retrofit2.Response

interface AuthRepository {
    suspend fun getLoginUser(token: String? = SingletonObject.token.value) : Response<LoginUser>
    suspend fun getUserData(userId: String) : Response<LoginUser>
    suspend fun login(loginReq : LoginReq) : Response<LoginRes>
    suspend fun signUp(userData : SignUpReq) : Response<SignUpRes>
//    suspend fun signUp(userData : SignUpReq, profileImageUrl : String?) : Response<SignUpRes>
    suspend fun googleLoginCallBack(googleLoginReq: GoogleLoginReq) : Response<GoogleLoginRes>
}