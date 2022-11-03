package com.heechan.membeder.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.GoogleLoginReq
import com.heechan.membeder.model.data.auth.GoogleLoginRes
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.DataStoreUtil
import com.heechan.membeder.util.State
import kotlinx.coroutines.*

class SplashViewModel(val application: Application) : ViewModel() {
    private val auth = AuthRepositoryImpl()
    private val dataStore = DataStoreUtil(application)

    val saveToken = dataStore.accessToken.asLiveData()

    val autoLoginState = MutableLiveData<State>()
    val userData = MutableLiveData<User>()

    val googleLoginState = MutableLiveData<State>()
    val googleCallBack = MutableLiveData<GoogleLoginRes>()

    fun autoLogin() {
        if (autoLoginState.value == State.LOADING)
            return


        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            autoLoginState.value = State.FAIL

            Log.e("loginTag", e.toString())
        }) {
            autoLoginState.value = State.LOADING
            getUserData(saveToken.value!!)
            autoLoginState.value = State.SUCCESS
        }
    }

    fun googleLogin(task: Task<GoogleSignInAccount>) {
        Log.d("googleLoginState", "실행함")
        if (googleLoginState.value == State.LOADING)
            return

        val account = task.getResult(ApiException::class.java)
        val token = account.idToken!!

        viewModelScope.launch (CoroutineExceptionHandler { _, e ->
            googleLoginState.value = State.FAIL
        }) {
            googleLoginState.value = State.LOADING

            Log.d("googleLoginState", "가져오려함")


            val response = withContext(Dispatchers.IO) {
                Log.d("googleLoginState", "가져오기 전")
                auth.getGoogleCallback(GoogleLoginReq(idToken = token))
            }

            Log.d("googleLoginState", "가져옴")

            if (response.isSuccessful) {
                val body = response.body()!!
                googleCallBack.value = body

                Log.d("googleLoginState", body.toString())

                if (body.registered) { // 회원가입 O
                    getUserData(body.accessToken)
                } else { // 회원가입 X
                    googleLoginState.value = State.SUCCESS
                }
            } else {
                googleLoginState.value = State.FAIL
            }

        }

        Log.d("googleLoginState", "함수 끝")
    }

    private suspend fun getUserData(token : String) {
        /** ViewModel에 있는 토큰을 가지고, 불러와서 유저 정보를 저장한다 */
        val response = withContext(Dispatchers.IO) {
            auth.getLoginUser(token)
        }

        if (response.isSuccessful) {
            val body = response.body()!!

            SingletonObject.userData = body.user
            SingletonObject.setToken(token, application)
            userData.value = body.user
        } else {
            autoLoginState.value = State.FAIL
            Log.d("loginTag", "실패 : ${response.errorBody()}")
        }
    }
}