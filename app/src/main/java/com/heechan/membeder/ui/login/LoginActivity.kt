package com.heechan.membeder.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityLoginBinding
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.util.ExtraKey
import com.heechan.membeder.util.State.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel: LoginViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(application) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        binding.btnLoginSubmit.setOnClickListener {
            viewModel.login()
        }

        viewModel.state.observe(this) {
            when (it) {
                SUCCESS -> {
                    gotoMain()
                }
                FAIL -> {
                    BadSnackBar.make(
                        view = binding.root,
                        title = "로그인에 실패했어요",
                        message = "로그인에 실패했어요. 다시 한번 확인해주세요.",
                    ).show()
                }
                LOADING -> {}
            }
        }
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(ExtraKey.USER_DATA.key, viewModel.resultUserData.value!!)
        startActivity(intent)
        finish()
    }
}