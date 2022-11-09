package com.heechan.membeder.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivitySignUpBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.GoogleLoginRes
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.util.ExtraKey
import com.heechan.membeder.util.LoginType
import com.heechan.membeder.util.State

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private lateinit var navController: NavController
    val viewModel : SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_register) as NavHostFragment
        navController = navHostFragment.navController

        val googleCallBack = intent.getParcelableExtra<GoogleLoginRes>(ExtraKey.GOOGLE_CALL_BACK.key)
        Log.d("[GoogleLogin]", googleCallBack.toString())
        if(googleCallBack != null) {
            with(viewModel) {
                loginType = LoginType.GOOGLE
                email.value = googleCallBack.email
                nickname.value = googleCallBack.name
            }
        }
        else {
            viewModel.loginType = LoginType.EMAIL
        }

        viewModel.state.observe(this){
            when(it){
                State.SUCCESS -> {
                    SingletonObject.userData = viewModel.resultSignUpData.value!!.user
                    SingletonObject.setToken(viewModel.resultSignUpData.value!!.accessToken, this)

                    navController.navigate(R.id.action_signUp8Fragment_to_signUp9Fragment)
                }
                State.LOADING -> {}
                State.FAIL -> {
                    BadSnackBar.make(binding.root, "회원가입 실패", "회원가입에 실파해셨습니다.\n다시한번 시도해주세요")
                }
            }
        }
    }

    val gotoMain : (View) -> Unit = {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}