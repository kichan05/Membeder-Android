package com.heechan.membeder.ui.team.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamDetailViewModel : ViewModel() {
    private val repository = TeamRepositoryImpl()
    val teamData = MutableLiveData<Team>()

    val joinRequestState = MutableLiveData<State>()

    fun getTeamData(teamId : String) {
        viewModelScope.launch {
            val response = repository.getTeamInfo(teamId)

            if(response.isSuccessful && response.body() != null) {
                val body = response.body()!!

                teamData.value = body.team
            }
        }
    }

    fun joinRequestTeam(teamId : String) {
        viewModelScope.launch(CoroutineExceptionHandler{ _, e ->
            Log.e("[TeamJoinReq]", e.message.toString())
        }) {
            val response = withContext(Dispatchers.IO) {
                repository.joinRequest(teamId)
            }

            if(response.isSuccessful) {
                joinRequestState.value = State.SUCCESS
            } else {
                joinRequestState.value = State.FAIL
            }
        }
    }
}