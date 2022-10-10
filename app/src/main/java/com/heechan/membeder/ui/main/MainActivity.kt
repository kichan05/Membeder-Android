package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.databinding.SnackbarGoodBinding
import com.heechan.membeder.ui.SplashActivity
import com.heechan.membeder.view.AlertDialog
import com.heechan.membeder.view.snack.CustomSnackBar
import com.heechan.membeder.view.SnackBarType
import com.heechan.membeder.view.snack.BadSnackBar
import com.heechan.membeder.view.snack.GoodSnackBar

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
            val snackBar = BadSnackBar.make(
                view = binding.root,
                title = "잘했어요",
                message = "멤비더에 오신걸 환영합니다."
            ).apply {
                show()
                setAction("확인"){
                    val intent = Intent(this@MainActivity, SplashActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}