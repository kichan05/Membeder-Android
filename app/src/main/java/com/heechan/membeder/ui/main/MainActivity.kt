package com.heechan.membeder.ui.main

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.util.ExtraKey

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.fragment_main)
        binding.bnvMain.setupWithNavController(navController)

        val userData = intent.getParcelableExtra<User>(ExtraKey.USER_DATA.key)
    }
}