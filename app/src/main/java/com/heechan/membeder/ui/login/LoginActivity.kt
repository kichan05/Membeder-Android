package com.heechan.membeder.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
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
            val imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0);

            viewModel.login()
        }

        viewModel.state.observe(this) {
            when (it) {
                SUCCESS -> {
                    Log.d("loginUserData", viewModel.responseBody.toString())
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
        val intent = Intent(this, MainActivity::class.java).apply {
//            putExtra(ExtraKey.USER_DATA.key, viewModel.responseBody.value!!.user)
//            putExtra(ExtraKey.ACCESS_TOKEN.key, viewModel.responseBody.value!!.accessToken)
        }


        startActivity(intent)
        finishAffinity()
    }
}