package com.heechan.membeder.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.contest.ContestRes
import com.heechan.membeder.model.data.team.TeamListRes
import com.heechan.membeder.model.remote.ContestRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContestViewModel : ViewModel(){
    private val contestRepository = ContestRepositoryImpl()
    val state = MutableLiveData<State>()

    val contestList = MutableLiveData<List<ContestRes>>()
    fun getContestData() {
        if (state.value == State.LOADING) {
            return
        }

        viewModelScope.launch(
            CoroutineExceptionHandler { _, e ->
                Log.e("[Get Contest]", e.toString())

                state.value = State.FAIL
            }
        ) {
            state.value = State.LOADING

            val response = withContext(Dispatchers.IO) {
                contestRepository.getContestList(SingletonObject.token.value!!)
            }

            if (response.isSuccessful && response.body() != null) {
                contestList.value = response.body()
                state.value = State.SUCCESS
            } else {
                Log.e("[Get Contest]", response.errorBody().toString())

                state.value = State.FAIL
            }
        }
    }
}