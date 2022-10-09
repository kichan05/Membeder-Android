package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.ui.SplashActivity
import com.heechan.membeder.view.AlertDialog
import com.heechan.membeder.view.CustomSnackBar
import com.heechan.membeder.view.SnackBarType

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnMainFill.setOnClickListener {
            val dialog = AlertDialog(
                title = "저는 제목 입니다",
                message = "저는 설명문입니다.",
                okCallBack = {
                    Toast.makeText(this, "확인 클릭", Toast.LENGTH_SHORT).show()
                }
            ).apply {
                show(supportFragmentManager, "alert dialog")
            }
        }

        binding.btnMainFillActive.setOnClickListener {
            val snackBar = CustomSnackBar.make(
                view = binding.btnMainFillActive,
                title = "저는 제목입니다.",
                message = "저는 메시지 입니당",
                type = SnackBarType.GOOD
            ).apply {
                show()
                setAction("확인") {
                    val intent = Intent(this@MainActivity, SplashActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}