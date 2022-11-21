package com.heechan.membeder.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.model.data.auth.*
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.DataStoreUtil
import com.heechan.membeder.util.LoginType
import com.heechan.membeder.util.State
import kotlinx.coroutines.*
import retrofit2.Response

class SplashViewModel(val application: Application) : ViewModel() {
    private val auth = AuthRepositoryImpl()
    private val dataStore = DataStoreUtil(application)

    val saveToken = dataStore.accessToken.asLiveData()
    val googleToken = MutableLiveData<String>()

    val state = MutableLiveData<State>()
    val loginType = MutableLiveData<LoginType>()
    val googleLoginCallBack = MutableLiveData<GoogleLoginRes>()
//    val loginResponseData = MutableLiveData<LoginUser>()

    fun autoLogin() {
        loginType.value = LoginType.EMAIL
        if (state.value == State.LOADING)
            return

        viewModelScope.launch(
            CoroutineExceptionHandler { _, e ->
                Log.e("[LoginTag]", e.toString())
                state.value = State.FAIL
            }
        ) {
            state.value = State.LOADING

            state.value = getUserData(saveToken.value!!)
        }
    }

    fun googleLogin(account: GoogleSignInAccount) {
        loginType.value = LoginType.GOOGLE
        googleToken.value = account.idToken

        if (googleToken.value == null) {
            state.value = State.FAIL
            return
        }

        viewModelScope.launch(
            CoroutineExceptionHandler { _, e ->
                Log.e("[GoogleLogin]", e.toString())
            }
        ) {
            state.value = State.LOADING

            val response = withContext(Dispatchers.IO) {
                auth.googleLoginCallBack(GoogleLoginReq(idToken = googleToken.value!!))
            }

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                Log.i("[GoogleLogin] googleCallBack", body.toString())

                googleLoginCallBack.value = body

                if (body.registered) {
                    // 기존에 회원가입 함

                    val res = getUserData(body.accessToken!!)
                    Log.d("GoogleLogin", res.toString())
                    state.value = res
                } else {
                    // 처음 접속하는 계정
                    state.value = State.SUCCESS
                }
            } else {
                Log.e("[GoogleLogin]", response.errorBody().toString())
                state.value = State.FAIL
            }
        }
    }

    private suspend fun getUserData(token: String): State {
        /** ViewModel에 있는 토큰을 가지고, 불러와서 유저 정보를 저장한다 */
        val response: Response<LoginUser> = withContext(Dispatchers.IO) {
            auth.getLoginUser(token)
        }

        return if (response.isSuccessful) {
            val body = response.body()!!

            saveSingletonData(body)

            Log.d("[loginTag]", body.toString())

            State.SUCCESS
        } else {
            Log.e("[loginTag]", "실패 : ${response.errorBody()}")
            State.FAIL
        }
    }

    private fun saveSingletonData(loginResponseData : LoginUser) {
        with(SingletonObject) {
            setToken(saveToken.value!!, application)
            setUserData(loginResponseData.user)
        }
    }
}