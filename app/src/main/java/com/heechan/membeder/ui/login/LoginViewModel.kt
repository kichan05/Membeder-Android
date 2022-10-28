package com.heechan.membeder.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.auth.LoginRequest
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.DataStoreUtil
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(val application: Application) : ViewModel() {
    private val auth = AuthRepositoryImpl()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val state = MutableLiveData<State>()
    val resultUserData = MutableLiveData<User>()

    fun login() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            state.value = State.FAIL

            Log.e("loginTag", e.toString())
        }) {
            state.value = State.LOADING
            val response = withContext(Dispatchers.IO) {
                auth.login(
                    LoginRequest(email = "ckstmznf0214@edcan.com", "qwer1234")
                )
            }

            if (response.isSuccessful) {
                val body = response.body()!!

                val dataStore = DataStoreUtil(application)
                dataStore.setAccessToken(body.accessToken)

                resultUserData.value = body.user
                state.value = State.SUCCESS
            } else {
                state.value = State.FAIL
                Log.d("loginTag", "실패 : ${response.errorBody()}")
            }
        }
    }
}