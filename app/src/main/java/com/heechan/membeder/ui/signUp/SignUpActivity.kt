package com.heechan.membeder.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivitySignUpBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.GoogleLoginRes
import com.heechan.membeder.ui.main.MainActivity
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

        // 구글로그인인지 체크, 로그인 타입에 따라서 뷰모델에 type변경
        val googleCallBack = intent.getParcelableExtra<GoogleLoginRes>(ExtraKey.GOOGLE_CALL_BACK.key)
        Log.d("[GoogleLogin]", googleCallBack.toString())
        if(googleCallBack != null) {
            with(viewModel) {
                loginType = LoginType.GOOGLE
                email.value = googleCallBack.email
            }
        }
        else {
            viewModel.loginType = LoginType.EMAIL
        }

        viewModel.state.observe(this){
            when(it){
                State.SUCCESS -> {
                    navController.navigate(R.id.action_signUp8Fragment_to_signUp9Fragment)
                }
                State.LOADING -> {}
                State.FAIL -> {
                    Toast.makeText(this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                    //TODO : 회원가입 프래그먼트에는 스낵바가 안보임, 임시로 토스트로 진행
//                    BadSnackBar.make(binding.root, "회원가입 실패", "회원가입에 실패하셨습니다.\n다시한번 시도해주세요")
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