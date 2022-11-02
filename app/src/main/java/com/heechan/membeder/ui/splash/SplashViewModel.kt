package com.heechan.membeder.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.gson.Gson
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.LoginReq
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.DataStoreUtil
import com.heechan.membeder.util.State
import kotlinx.coroutines.*

class SplashViewModel(application: Application) : ViewModel() {
    private val auth = AuthRepositoryImpl()
    private val dataStore = DataStoreUtil(application)

    val saveLoginData = dataStore.loginData.asLiveData()

    val state = MutableLiveData<State>()
    val userDate = MutableLiveData<User>()

    fun login() {
        if(state.value == State.LOADING)
            return

        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            state.value = State.FAIL

            Log.e("loginTag", e.toString())
        }) {
            state.value = State.LOADING

            val loginReq = Gson().fromJson(saveLoginData.value!!, LoginReq::class.java)

            val response = withContext(Dispatchers.IO) {
                auth.login(loginReq)
            }

            if (response.isSuccessful) {
                val body = response.body()!!

                SingletonObject.userData = body.user
                SingletonObject.token = body.accessToken

                userDate.value = body.user
                state.value = State.SUCCESS
            } else {
                state.value = State.FAIL
                Log.d("loginTag", "실패 : ${response.errorBody()}")
            }
        }
    }
}