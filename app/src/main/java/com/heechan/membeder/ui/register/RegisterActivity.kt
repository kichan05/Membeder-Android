package com.heechan.membeder.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityRegisterBinding
import com.heechan.membeder.util.State

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    val viewModel : RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_register) as NavHostFragment
        val navController = navHostFragment.navController

        viewModel.state.observe(this){
            when(it){
                State.SUCCESS -> {
                    Toast.makeText(this, "회원가입에 성공 했습니다. ${viewModel.resultUserData.value!!.name}님", Toast.LENGTH_SHORT).show()
                }
                State.LOADING -> {
                }
                State.FAIL -> {
                    Toast.makeText(this, "회원가입에 실패함 ㅅㄱ ㅂ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}