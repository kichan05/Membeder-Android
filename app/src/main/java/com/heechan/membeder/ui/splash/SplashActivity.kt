package com.heechan.membeder.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.heechan.membeder.BuildConfig
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivitySplashBinding
import com.heechan.membeder.ui.login.LoginActivity
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.ui.signUp.SignUpActivity
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.util.ExtraKey
import com.heechan.membeder.util.State.*

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


        viewModel.googleLoginState.observe(this) {
            val data = viewModel.googleCallBack.value!!

            if(data.registered){
                gotoMain()
            }
            else {
                val intent = Intent(this, SignUpActivity::class.java).apply {
                    putExtra(ExtraKey.GOOGLE_CALL_BACK.key, data)
                }
                startActivity(intent)
            }
        }

        binding.btnSplashStart.setOnClickListener(gotoRegister)
        binding.txtSplashGotoLogin.setOnClickListener(gotoLogin)
        binding.btnLoginGoogleLogin.setOnClickListener(googleLogin)

        viewModel.saveToken.observe(this) {
            Log.d("saveLoginData", it)
            if (it != "") {
                viewModel.autoLogin()
            }
        }

        viewModel.autoLoginState.observe(this) {
            when (it) {
                SUCCESS -> {
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
        }

        viewModel.googleLoginState.observe(this) {
            Log.d("googleLoginState", it.toString())
            when (it) {
                SUCCESS -> {
                    if(viewModel.googleCallBack.value!!.registered) {
                        gotoMain()
                    }
                    else {
                        val intent = Intent(this, SignUpActivity::class.java).apply {
                            putExtra(ExtraKey.GOOGLE_CALL_BACK.key, viewModel.googleCallBack.value!!)
                        }
                        startActivity(intent)
                    }
                }
                LOADING -> {}
                FAIL -> {
                    BadSnackBar.make(
                        view = binding.root,
                        title = "구글 로그인 실패",
                        message = "계정 정보를 가죠오는데 실패했어요.\n다시 로그인 해주세요."
                    ).show()
                }
            }
        }
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
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
                viewModel.googleLogin(task)
            }
        }
    }

    private val gotoRegister: (View) -> Unit = {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private val gotoLogin: (View) -> Unit = {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val GOOGLE_SIGN_IN = 312
    }
}