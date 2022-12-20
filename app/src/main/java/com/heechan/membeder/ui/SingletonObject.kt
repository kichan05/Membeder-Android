package com.heechan.membeder.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.util.DataStoreUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SingletonObject : ViewModel() {
    val token = MutableLiveData<String>()
    val userData = MutableLiveData<User>()
    val userTeamList = MutableLiveData<MutableList<Team>>()
    val selectTeam = MutableLiveData<Team>()
    val userTeamCount = MutableLiveData<Int>()
    val userPortfolioCount = MutableLiveData<Int>()


    fun setToken(token: String, context: Context) {
        val dataStore = DataStoreUtil(context)
        CoroutineScope(Dispatchers.Main).launch {
            dataStore.setAccessToken(token)
        }

        SingletonObject.token.value = token
    }

    fun setUserData(userData : User) {
        Log.d("SingletonObject", "setUserData ${userData.teamList}")

        this.userData.value = userData
        userTeamList.value = userData.teamList.toMutableList()
        userTeamCount.value = userData.teamList.size
        Log.d("SingletonObject Count", userTeamCount.value.toString())

        if(userData.teamList.isNotEmpty()) {
            selectTeam.value = userData.teamList[0]
        }
    }
}