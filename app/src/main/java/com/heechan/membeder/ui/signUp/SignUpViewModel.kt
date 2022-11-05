package com.heechan.membeder.ui.signUp

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.R
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.LoginReq
import com.heechan.membeder.model.data.auth.SignUpReq
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.DataStoreUtil
import com.heechan.membeder.util.LoginType
import com.heechan.membeder.util.State
import kotlinx.coroutines.*

class SignUpViewModel(application: Application) : ViewModel() {
    private val auth = AuthRepositoryImpl()
    private val dataStore = DataStoreUtil(application)

    lateinit var loginType : LoginType
    val nickname = MutableLiveData<String>()    // 닉네임
    val email = MutableLiveData<String>()       // 이메일
    val password = MutableLiveData<String>()    // 비밀번호
    val passwordRe = MutableLiveData<String>()  // 비밀번호 다시 입력
    val name = MutableLiveData<String>()        // 이름
    val websiteUrl = MutableLiveData<String>("")  // 소개 링크
    val age = MutableLiveData<Int>()            // 나이
    val profileImage = MutableLiveData<Uri>()   // 프로필 이미지
    val introduceMessage = MutableLiveData<String>("")    // 소개 문구
    val profession = MutableLiveData<Int>(R.id.rb_signUp3_developer)  // 직종
    val career = MutableLiveData<String>("")      // 경력
    val stack = MutableLiveData<String>("")       // 기술 스택
    val department = MutableLiveData<String>("")  // 분야

    val state = MutableLiveData<State>()
    val errorMessage = MutableLiveData<String>()
    val resultUserData = MutableLiveData<User?>(null)

    val professionString: String
        get() = when (profession.value!!) {
            R.id.rb_signUp3_developer -> "개발자"
            R.id.rb_signUp3_design -> "디자이너"
            else -> "그 외"
        }

    val registerReq: SignUpReq
        get() = SignUpReq(
            type = loginType.type,
            name = name.value!!,
            nickname = nickname.value!!,
            birth = "2022-09-25T08:50:21.996Z",
            picture = "URL",
            email = email.value!!,
            password = password.value!!,
            profession = professionString,
            career = career.value!!.toInt(),
            website = websiteUrl.value!!,
            introduce = introduceMessage.value!!,
            stack = stack.value!!,
            department = department.value!!,
        )

    fun signUp() {
        if (state.value == State.LOADING)
            return

        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            // 에러가 발생 했을때
            state.value = State.FAIL
            Log.e("[registerError]", e.toString())
        }) {
            val registerReq = this@SignUpViewModel.registerReq

            state.value = State.LOADING
            val result = withContext(Dispatchers.IO) {
                // 서버에 회원 가입을 요청
                auth.signUp(registerReq)
            }

            if (result.isSuccessful) {
                // 회원가입에 성공 한 경우
                val body = result.body() ?: return@launch

//                dataStore.accessToken = body.accessToken
                // TODO: 엑세스 토큰을 저장해서 로그인

                SingletonObject.userData = body.user

                resultUserData.value = body.user
                state.value = State.SUCCESS
            } else {
                // 회원가입에 실패 한 경우

                state.value = State.FAIL
            }
        }
    }

//    fun inputCheckSignUp1() {
//        if(email.value.isNullOrBlank()) {
//            errorMessage.
//        }
//    }
}