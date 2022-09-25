package com.heechan.membeder.ui.register

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.User
import com.heechan.membeder.model.remote.AuthRepository
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.model.service.AuthService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RegisterViewModel : ViewModel() {
    private val auth = AuthRepositoryImpl()

    val nickname = MutableLiveData<String>()    // 닉네임
    val email = MutableLiveData<String>()       // 이메일
    val password = MutableLiveData<String>()    // 비밀번호
    val passwordRe = MutableLiveData<String>()  // 비밀번호 다시 입력
    val websiteUrl = MutableLiveData<String>()  // 소개 링크
    val age = MutableLiveData<Int>()            // 나이
    val profileImage = MutableLiveData<Uri>()   // 프로필 이미지
    val introduceMessage = MutableLiveData<String>()    // 소개 문구
    val profession = MutableLiveData<String>()  // 직종
    val career = MutableLiveData<String>()      // 경력
    val stack = MutableLiveData<String>()       // 기술 스택
    val department = MutableLiveData<String>()  // 분야


    fun register() {
        viewModelScope.launch {
            val userData = User(
                accountType = "email",
                name = "바키찬",
                nickname = "지이너스 디벨로퍼",
                email = "ckstmznf0214@naver.com",
                password = "qwer1234",
                profession = "개발자",
                career = 0,
                website = "https://www.github.com/kichan05",
                introduce = "아이엠 지니어스 디벨로퍼",
                stack = "Python",
                department = "지니어스"
            )

            auth.signUp(userData)
        }
    }

}