package com.heechan.membeder.ui.team.manage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamPermissionRepositoryImpl
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorityManageViewModel : ViewModel(){
    private val repository = TeamRepositoryImpl()

    val teamData = MutableLiveData<Team>()
    val switchData = MutableLiveData<List<Boolean>>()

    val state = MutableLiveData<State>()
    val errorMessage = MutableLiveData<String?>()

    fun getTeamData(teamId : String) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getTeamInfo(teamId)
            }

            if(response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                teamData.value = body.team

                body.team.member.filter { it.id != SingletonObject.userData.value!!.id }.map { it.id }.forEach {
                    switchData.value = switchData.value?.plus(false)
                }
            }
        }
    }
//    fun addPermission(){
//        Log.d("[PermissionAdd]","")
//
//        if (state.value == State.LOADING) {
//            return
//        }
//        viewModelScope.launch (CoroutineExceptionHandler { _, e ->
//            Log.e("[PermissionAdd]", e.toString())
//
//            state.value = State.FAIL
//        }){
//            state.value = State.LOADING
//            val userId = SingletonObject.userData.value!!.id
//            val teamId = SingletonObject.selectTeam.value!!.id
//
//            val result = withContext(Dispatchers.IO){
//                authority.addPermission(teamData = teamId, userData = userId)
//            }
//            if (result.isSuccessful && result.body() != null) {
//                val body = result.body()!!
//
//                state.value = State.SUCCESS
//            } else {
//                state.value = State.FAIL
//            }
//        }
//    }
//    fun deletePermission(){
//        Log.d("[PermissionDelete]","")
//
//        if (state.value == State.LOADING) {
//            return
//        }
//        viewModelScope.launch (CoroutineExceptionHandler { _, e ->
//            Log.e("[PermissionDelete]", e.toString())
//
//            state.value = State.FAIL
//        }){
//            state.value = State.LOADING
//            val userId = SingletonObject.userData.value!!.id
//            val teamId = SingletonObject.selectTeam.value!!.id
//
//            val result = withContext(Dispatchers.IO){
//                authority.deletePermission(teamData = teamId, userData = userId)
//            }
//            if (result.isSuccessful && result.body() != null) {
//                val body = result.body()!!
//
//                state.value = State.SUCCESS
//            } else {
//                state.value = State.FAIL
//            }
//        }
//    }
}