package com.heechan.membeder.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.util.DataStoreUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SingletonObject {
    fun setToken(token: String, context: Context) {
        val dataStore = DataStoreUtil(context)
        CoroutineScope(Dispatchers.Main).launch {
            dataStore.setAccessToken(token)
        }

        SingletonObject.token.value = token
    }

    val userData = MutableLiveData<User>()
    val selectTeam = MutableLiveData<Team>()
//    val selectTeamIndex = MutableLiveData<Int>()
    val token = MutableLiveData<String>()
}