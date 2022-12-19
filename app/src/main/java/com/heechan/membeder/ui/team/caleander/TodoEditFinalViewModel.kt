package com.heechan.membeder.ui.team.caleander

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.schedule.ScheduleAddReq
import com.heechan.membeder.model.remote.ScheduleRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class TodoEditFinalViewModel : ViewModel() {
    private val repository = ScheduleRepositoryImpl()

    val name = MutableLiveData<String>()
    val account = MutableLiveData<String>()
    val deadLine = MutableLiveData<LocalDate>()

    val state = MutableLiveData<State>()
    val errorMessage = MutableLiveData<String?>()


    fun addSchedule() {
        Log.d("[ScheduleAdd]", name.value.toString())

        if (state.value == State.LOADING) {
            return
        }

        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            Log.e("[ScheduleAdd]", e.toString())

            state.value = State.FAIL
        }) {
            state.value = State.LOADING

            val teamId = SingletonObject.selectTeam.value!!.id
            val request = ScheduleAddReq(
                name = name.value!!,
                description = account.value ?: "",
                complete = false,
                deadLine = deadLine.value.toString()
            )

            val response = withContext(Dispatchers.IO) {
                repository.addSchedule(
                    teamId = teamId, scheduleData = request
                )
            }

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!

                state.value = State.SUCCESS
            } else {
                state.value = State.FAIL
            }
        }
    }

    fun inputCheckName() : Boolean {
        if(name.value.isNullOrBlank()) {
            errorMessage.value = "일정 이름을 입력해주세요."
            return false
        }
        errorMessage.value = null
        return true
    }

    fun inputCheckDeadLine() : Boolean {
        if(deadLine.value == null) {
            errorMessage.value = "마감일을 선택해주세요."
            return false
        }

        errorMessage.value = null
        return true
    }
}