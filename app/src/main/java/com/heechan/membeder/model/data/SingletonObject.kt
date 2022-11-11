package com.heechan.membeder.model.data

import androidx.lifecycle.MutableLiveData
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.team.Team

object SingletonObject {
    val userData = MutableLiveData<User>()
    val selectTeam = MutableLiveData<Team>()
    val selectTeamIndex = MutableLiveData<Int>()
    val token = MutableLiveData<String>()
}