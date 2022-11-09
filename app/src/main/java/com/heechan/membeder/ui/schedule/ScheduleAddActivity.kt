package com.heechan.membeder.ui.schedule

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityScheduleAddBinding
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import com.heechan.membeder.util.State
import com.heechan.membeder.util.State.*

class ScheduleAddActivity :
    BaseActivity<ActivityScheduleAddBinding>(R.layout.activity_schedule_add) {
    private lateinit var navController: NavController
    val viewModel : ScheduleAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_schedule) as NavHostFragment
        navController = navHostFragment.navController

        viewModel.state.observe(this) {
            when (it) {
                SUCCESS -> {
                    Toast.makeText(this, "일정을 추가했어요", Toast.LENGTH_SHORT).show()
                    finish()
                }
                LOADING -> {}
                FAIL -> {
                    BadSnackBar.make(binding.root, "스케쥴 추가 실패", "스케줄 추가에 실패했어요", 1000).show()
                }
            }
        }
    }

    fun gotoNext(index : Int) {
        when(index) {
            0 -> {
                navController.navigate(R.id.action_scheduleNameFragment_to_scheduleAccountFragment)
            }
            1 -> {
                navController.navigate(R.id.action_scheduleAccountFragment_to_scheduleDeadLineFragment)
            }
        }
    }
}