package com.heechan.membeder.ui.team.manage

import android.util.Log
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

class MainTeamManageViewModel: ViewModel() {
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


}