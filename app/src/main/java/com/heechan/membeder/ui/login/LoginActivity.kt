package com.heechan.membeder.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityLoginBinding
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.util.State
import com.heechan.membeder.util.State.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        binding.btnLoginSubmit.setOnClickListener {
            viewModel.login()
        }

        viewModel.state.observe(this) {
            when (it) {
                SUCCESS -> {
                    Toast.makeText(this, "환영합니다.", Toast.LENGTH_SHORT).show()
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
}