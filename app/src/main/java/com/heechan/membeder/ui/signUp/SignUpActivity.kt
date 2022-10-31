package com.heechan.membeder.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivitySignUpBinding
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.ui.teamMake.TeamMakeActivity
import com.heechan.membeder.util.ExtraKey
import com.heechan.membeder.util.State

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
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
        val navController = navHostFragment.navController

        viewModel.state.observe(this){
            Log.d("registerState", it.toString())
            when(it){
                State.SUCCESS -> {
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(ExtraKey.USER_DATA.key, viewModel.resultUserData.value!!)
                    }
                    startActivity(intent)
                    finishAffinity()
                }
                State.LOADING -> {
                }
                State.FAIL -> {
                    Toast.makeText(this, "회원가입에 실패함 ㅅㄱ ㅂ", Toast.LENGTH_SHORT).show()
                }
            }
        }
//        binding.profileTeammaketestBtn.setOnClickListener {
//            startActivity(Intent(this, TeamMakeActivity::class.java))
//        }
    }
}