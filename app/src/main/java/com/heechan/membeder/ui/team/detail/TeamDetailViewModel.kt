package com.heechan.membeder.ui.team.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import kotlinx.coroutines.launch

class TeamDetailViewModel : ViewModel() {
    private val repository = TeamRepositoryImpl()
    val teamData = MutableLiveData<Team>()

    fun getTeamData(teamId : String) {
        viewModelScope.launch {
            val response = repository.getTeamInfo(teamId)

            if(response.isSuccessful && response.body() != null) {
                val body = response.body()!!

                teamData.value = body.team
            }
        }
    }
}