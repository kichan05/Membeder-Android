package com.heechan.membeder.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.GoogleLoginReq
import com.heechan.membeder.model.data.auth.GoogleLoginRes
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.DataStoreUtil
import com.heechan.membeder.util.LoginType
import com.heechan.membeder.util.State
import kotlinx.coroutines.*
import kotlin.math.log

class SplashViewModel(val application: Application) : ViewModel() {
    private val auth = AuthRepositoryImpl()
    private val dataStore = DataStoreUtil(application)

    val saveToken = dataStore.accessToken.asLiveData()

    val state = MutableLiveData<State>()
    val loginType = MutableLiveData<LoginType>()
    val googleLoginCallBack = MutableLiveData<GoogleLoginRes>()
    val userData = MutableLiveData<User>()

    fun autoLogin() {
        loginType.value = LoginType.EMAIL
        if (state.value == State.LOADING)
            return

        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            state.value = State.FAIL

            Log.e("loginTag", e.toString())
        }) {
            state.value = State.LOADING
            getUserData(saveToken.value!!)
            state.value = State.SUCCESS
        }
    }

    fun googleLogin(account : GoogleSignInAccount) {
        loginType.value = LoginType.GOOGLE
        val idToken = account.idToken
        if(idToken == null){
            state.value = State.FAIL
            return
        }

        viewModelScope.launch(CoroutineExceptionHandler{ _, e ->
            Log.e("[GoogleLogin]", e.toString())
        }) {
            state.value = State.LOADING

            val response = withContext(Dispatchers.IO) {
                auth.googleLoginCallBack(GoogleLoginReq(idToken = idToken))
            }

            if(response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                Log.i("[GoogleLogin] googleCallBack", body.toString())

                googleLoginCallBack.value = body

                if(body.registered){
                    // 기존에 회원가입 함
                }
                else {
                    // 처음 접속하는 계정

                    state.value = State.SUCCESS
                }
            }
            else {
                Log.e("[GoogleLogin]", response.errorBody().toString())
                state.value = State.FAIL
            }

        }
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
            state.value = State.FAIL
            Log.d("loginTag", "실패 : ${response.errorBody()}")
        }
    }
}