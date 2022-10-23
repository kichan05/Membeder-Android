package com.heechan.membeder.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.heechan.membeder.BuildConfig
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivitySplashBinding
import com.heechan.membeder.model.data.auth.GoogleLoginRequest
import com.heechan.membeder.model.service.AuthService
import com.heechan.membeder.ui.login.LoginActivity
import com.heechan.membeder.ui.signUp.SignUpActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSplashStart.setOnClickListener(gotoRegister)
        binding.txtSplashGotoLogin.setOnClickListener(gotoLogin)
        binding.btnSplashGoogleLogin.setOnClickListener(googleLogin)
    }

    private val googleLogin: (View) -> Unit = {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.OAUTH_GOOGLE_ID)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            GOOGLE_SIGN_IN -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)

                val account = task.getResult(ApiException::class.java)
                val token = account.idToken

                val authService = getRetrofitService()

                Log.d("googleLogin", token!!)
                CoroutineScope(Dispatchers.IO).launch {
                    val googleCallback = authService.googleCallBack(
                        GoogleLoginRequest(idToken = token!!)
                    )

                    Log.d("googleLogin", googleCallback.body().toString())
                }

            }
        }
    }

    private fun getRetrofitService(): AuthService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val clientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(clientBuilder.build())
            .build()

        return retrofit.create(AuthService::class.java)
    }

    private val gotoRegister: (View) -> Unit = {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    private val gotoLogin: (View) -> Unit = {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val GOOGLE_SIGN_IN = 312
    }
}