package com.heechan.membeder.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.LoginReq
import com.heechan.membeder.model.data.auth.LoginRes
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
    private val dataStore = DataStoreUtil(application)

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val state = MutableLiveData<State>()
    val errorMessage = MutableLiveData<String?>()
    val responseBody = MutableLiveData<LoginRes>()


    fun login() {
        if(state.value == State.LOADING)
            return

        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            state.value = State.FAIL

            Log.e("loginTag", e.toString())
        }) {
            state.value = State.LOADING

            val loginRequest = LoginReq(email = email.value!!, password=password.value!!)

            val response = withContext(Dispatchers.IO) {
                auth.login(loginRequest)
            }

            if (response.isSuccessful) {
                val body = response.body()!!

                SingletonObject.userData.value = body.user
                SingletonObject.setToken(body.accessToken, application)

                responseBody.value = body
                state.value = State.SUCCESS
            } else {
                state.value = State.FAIL
                Log.e("loginTag", "실패 : ${response.errorBody()}")
            }
        }
    }

    fun inputCheck() : Boolean {
        if(email.value.isNullOrBlank()) {
            errorMessage.value = "이메일을 입력해주세요."
            return false
        }

        if(password.value.isNullOrBlank()) {
            errorMessage.value = "비밀번호를 입력해주세요."
            return false
        }

        return true;
    }
}