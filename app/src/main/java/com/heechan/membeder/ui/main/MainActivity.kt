package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.profile.ProfileActivity
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import com.heechan.membeder.util.ExtraKey

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.fragment_main)
        binding.bnvMain.setupWithNavController(navController)

        GoodSnackBar.make(
            binding.root,
            "${SingletonObject.userData.value!!.nickname}님 환영합니다.",
            "멤비더에 어서와요.\n함께 일을 해봐요",
            700,
        ).show()
    }

    fun gotoTeamBuilding() {
        Log.e("btnHomeNoTeamGotoTeamBuildingEvent", "실행")
        navController.navigate(R.id.action_menu_main_home_to_menu_main_teamBuilding)
    }
}