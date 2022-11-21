package com.heechan.membeder.ui.team.make

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.team.Applicant
import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamMakeViewModel : ViewModel() {
    private val team = TeamRepositoryImpl()

    val teamName = MutableLiveData<String>()
    val teamDescription = MutableLiveData<String>()
    val teamLogo = MutableLiveData<Uri>()

    val teamApplicantDeveloper = MutableLiveData<String>()
    val teamApplicantDesigner = MutableLiveData<String>()
    val teamApplicantDirector = MutableLiveData<String>()

    val state = MutableLiveData<State>()
    val resultData = MutableLiveData<Team>()
    val errorMessage = MutableLiveData<String?>()

    fun makeTeam() {
        if(state.value == State.LOADING)
            return

        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            // 에러가 발생 했을때
            state.value = State.FAIL
            Log.e("[teamMakeError]", e.message.toString())
        }) {
            val teamMakeReq = CreateTeamReq(
                name = teamName.value!!,
                description = teamDescription.value!!,
                private = true,
                image = "image url",
                applicant = Applicant(
                    developer = teamApplicantDeveloper.value?.toIntOrNull() ?: 0,
                    designer = teamApplicantDesigner.value?.toIntOrNull() ?: 0,
                    director = teamApplicantDirector.value?.toIntOrNull() ?: 0,
                )
            )

            state.value = State.LOADING
            val result = withContext(Dispatchers.IO) {
                team.createTeam(teamMakeReq)
            }

            if (result.isSuccessful) {
                // 팀생성 성공
                val body = result.body() ?: return@launch

                resultData.value = body.team
                state.value = State.SUCCESS
            } else {
                // 팀생성 실패

                state.value = State.FAIL
            }
        }
    }

    fun inputCheckName() : Boolean {
        if(teamName.value.isNullOrBlank()){
            errorMessage.value = "팀 이름을 입력해주세요."
            return false
        }

        return true
    }
}