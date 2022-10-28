package com.heechan.membeder.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.DataStoreUtil
import com.heechan.membeder.util.State
import kotlinx.coroutines.*

class SplashViewModel(application: Application) : ViewModel() {
    private val repositoryImpl = AuthRepositoryImpl()
    private val dataStore = DataStoreUtil(application)

    val state = MutableLiveData<State>()
    val userDate = MutableLiveData<User>()
    val accessToken = dataStore.accessToken.asLiveData()

    fun getUserData() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            Log.e("getUserData", e.toString())

            state.value = State.FAIL
        }) {
            state.value = State.LOADING

            val response = withContext(Dispatchers.IO){
                repositoryImpl.getLoginUser()
            }

            if(response.isSuccessful) {
               val body = response.body()!!

                userDate.value = body.user
                state.value = State.SUCCESS
            }
            else {
                state.value = State.FAIL
            }

        }
    }
}