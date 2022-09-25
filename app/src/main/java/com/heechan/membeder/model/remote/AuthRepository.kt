package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.User

interface AuthRepository {
    suspend fun signUp(userData : User) : Int
}