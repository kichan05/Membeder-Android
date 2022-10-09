package com.heechan.membeder.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.auth.LoginRequest
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val auth = AuthRepositoryImpl()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val state = MutableLiveData<State>()
    val resultUserData = MutableLiveData<User>()

    fun login() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            state.value = State.FAIL
        }) {
            val loginReq = LoginRequest(email = "ckstmznf@naver.com", "qwer1234")

            state.value = State.LOADING
            val response = withContext(Dispatchers.IO) {
                auth.login(loginReq)
            }

            if(response.isSuccessful){
                resultUserData.value = response.body().accessToken ?: return@launch

                state.value = State.SUCCESS
            }
            else {
                state.value = State.FAIL
            }
        }
    }
}