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
    val token = MutableLiveData<String>()
    val userData = MutableLiveData<User>()
    val userTeamList = MutableLiveData<List<Team>>()
    val selectTeam = MutableLiveData<Team>()

    fun setToken(token: String, context: Context) {
        val dataStore = DataStoreUtil(context)
        CoroutineScope(Dispatchers.Main).launch {
            dataStore.setAccessToken(token)
        }

        SingletonObject.token.value = token
    }

    fun setUserData(userData : User) {
        this.userData.value = userData
        userTeamList.value = userData.teamList
        if(userData.teamList.isNotEmpty()) {
            selectTeam.value = userData.teamList[0]
        }
    }
}