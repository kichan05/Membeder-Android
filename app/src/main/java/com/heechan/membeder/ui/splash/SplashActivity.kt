package com.heechan.membeder.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.heechan.membeder.BuildConfig
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivitySplashBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.ui.login.LoginActivity
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.ui.signUp.SignUpActivity
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.util.ExtraKey
import com.heechan.membeder.util.LoginType
import com.heechan.membeder.util.State.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val viewModel: SplashViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SplashViewModel(application) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSplashStart.setOnClickListener(gotoRegister)
        binding.txtSplashGotoLogin.setOnClickListener(gotoLogin)
        binding.btnLoginGoogleLogin.setOnClickListener(googleLogin)

        viewModel.saveToken.observe(this) {
            Log.d("saveLoginData", it)
            if (it != "") {
                viewModel.autoLogin()
            }
        }

        viewModel.state.observe(this) {
            if (viewModel.loginType.value == LoginType.EMAIL) {
                when (it) {
                    SUCCESS -> {
                        SingletonObject.userData = viewModel.loginResponseData.value!!.user
                        SingletonObject.setToken(viewModel.loginResponseData.value!!.accessToken, this)

                        Log.d("LoginToken", SingletonObject.token)

                        gotoMain()
                    }
                    LOADING -> {}
                    FAIL -> {
                        BadSnackBar.make(
                            view = binding.root,
                            title = "자동 로그인 실패",
                            message = "계정 정보를 가죠오는데 실패했어요.\n다시 로그인 해주세요."
                        ).show()
                    }
                }
            } else if (viewModel.loginType.value == LoginType.GOOGLE) {
                when (it) {
                    SUCCESS -> {
                        if(viewModel.googleLoginCallBack.value!!.registered){

                        }
                        else {
                            val intent = Intent(this, SignUpActivity::class.java).apply {
                                putExtra(ExtraKey.GOOGLE_CALL_BACK.key, viewModel.googleLoginCallBack.value!!)
                            }
                            startActivity(intent)
                        }
                    }
                    LOADING -> {}
                    FAIL -> {}
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            GOOGLE_SIGN_IN -> {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)

                try {
                    val account = task.getResult(ApiException::class.java)
                    viewModel.googleLogin(account)
                } catch (e: ApiException) {

                }
            }
        }
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private val gotoRegister: (View) -> Unit = {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private val googleLogin: (View) -> Unit = {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(BuildConfig.OAUTH_GOOGLE_ID)
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }

    private val gotoLogin: (View) -> Unit = {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val GOOGLE_SIGN_IN = 312 //3월 12일은 내 생일~
    }
}