package com.heechan.membeder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivitySplashBinding
import com.heechan.membeder.ui.login.LoginActivity
import com.heechan.membeder.ui.register.RegisterActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSplashStart.setOnClickListener(gotoRegister)
        binding.txtSplashGotoLogin.setOnClickListener(gotoLogin)
    }

    private val gotoRegister : (View) -> Unit = {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private val gotoLogin : (View) -> Unit = {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}