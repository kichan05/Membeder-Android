package com.heechan.membeder.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.util.ExtraKey

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.fragment_main)
        binding.bnvMain.setupWithNavController(navController)
    }

    fun gotoTeamBuilding() {
        Log.e("btnHomeNoTeamGotoTeamBuildingEvent", "실행")
        navController.navigate(R.id.action_menu_main_home_to_menu_main_teamBuilding)
    }
}