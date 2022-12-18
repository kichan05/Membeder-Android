package com.heechan.membeder.ui.team.manage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemberBanViewModel: ViewModel() {
    private val repository = TeamRepositoryImpl()

    val teamData = MutableLiveData<Team>()

    fun getTeamData(teamId : String) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getTeamInfo(teamId)
            }

            if(response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                teamData.value = body.team
            }
        }
    }
}