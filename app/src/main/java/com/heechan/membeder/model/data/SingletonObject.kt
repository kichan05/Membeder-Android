package com.heechan.membeder.model.data

import android.content.Context
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.util.DataStoreUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SingletonObject {
    private var _userData: User? = null
    var userData : User?
        get() = _userData
        set(value) {
            _userData = value
        }

    private var _selectTeamIndex : Int = 0
    var selectTeamIndex : Int
        get() = _selectTeamIndex
        set(value) {
            _selectTeamIndex = value
        }

    private var _token: String? = null
    val token : String
        get() = _token!!

    fun setToken(token: String, context: Context) {
        val dataStore = DataStoreUtil(context)
        CoroutineScope(Dispatchers.Main).launch {
            dataStore.setAccessToken(token)
        }

        _token = token
    }
}