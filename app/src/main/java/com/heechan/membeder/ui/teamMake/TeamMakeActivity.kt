package com.heechan.membeder.ui.teamMake

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTeamMakeBinding
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.util.State

class TeamMakeActivity : BaseActivity<ActivityTeamMakeBinding>(R.layout.activity_team_make) {
    lateinit var navController: NavController
    val viewModel : TeamMakeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_teamMake) as NavHostFragment
        navController = navHostFragment.navController

        viewModel.state.observe(this){
            Log.d("teamCreateState", it.toString())
            when(it){
                State.SUCCESS -> {
                    Toast.makeText(this, "팀 생성에 성공했습니다. ${viewModel.resultData.value!!.name}", Toast.LENGTH_SHORT).show()
                    finish()
                }
                State.LOADING -> {
                }
                State.FAIL -> {
                    Toast.makeText(this, "팀 생성 실패함 ㅅㄱ ㅋㅋ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}