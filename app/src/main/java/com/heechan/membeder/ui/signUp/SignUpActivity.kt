package com.heechan.membeder.ui.signUp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
import com.heechan.membeder.ui.teamMake.TeamMakeActivity
import com.heechan.membeder.util.ExtraKey
import com.heechan.membeder.util.LoginType
import com.heechan.membeder.util.State

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private lateinit var navController: NavController

    val viewModel : SignUpViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SignUpViewModel(application) as T
            }
        }
    }


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
            Log.d("[register]", it.toString())
            when(it){
                State.SUCCESS -> {
                    SingletonObject.userData = viewModel.resultUserData.value!!
                    navController.navigate(R.id.action_signUp8Fragment_to_signUp9Fragment)
                }
                State.LOADING -> {}
                State.FAIL -> {
                    Log.d("[register]", "에러")
                    Toast.makeText(this, "회원가입에 실패함 ㅅㄱ ㅂ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val gotoMain : (View) -> Unit = {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}