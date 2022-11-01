package com.heechan.membeder.ui.teamMake

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.team.Applicant
import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.util.DataStoreUtil
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamMakeViewModel : ViewModel() {
    private val team = TeamRepositoryImpl()

    val state = MutableLiveData<State>()
    val resultData = MutableLiveData<Team?>(null)

    fun makeTeam(){
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            // 에러가 발생 했을때
            state.value = State.FAIL
            Log.d("[teamMakeError]", e.message.toString())
        }) {

        val teamMakeReq = CreateTeamReq(
            name = "멤비더 팀",
            description = "멤비더를 만드는 팀입니다",
            private = true,
            image = "image url",
            applicant = Applicant(designer = 1, developer = 2, director = 1)
        )

            state.value = State.LOADING
            val result = withContext(Dispatchers.IO) {
                // 서버에 팀생성 요청
                team.createTeam("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Ijc3NWQ0NTUxLTYyODYtNDEwZC04ZmVjLTlkYjdlZjI1NzJkZiIsImlhdCI6MTY2NzI5OTY4NCwiZXhwIjoxNjY5ODkxNjg0fQ.ivZqC7NnRM6jx27dNd5_0F90AuTooBIQi1O_jRT-Bvg", teamMakeReq)
            }

            if (result.isSuccessful) {
                // 팀생성 성공
                val body = result.body() ?: return@launch

                resultData.value = body
                state.value = State.SUCCESS
            } else {
                // 팀생성 실패

                state.value = State.FAIL
            }
        }
    }
}