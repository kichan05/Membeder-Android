package com.heechan.membeder.ui.team.caleander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTodoEditBinding
import com.heechan.membeder.databinding.ActivityTodoFinalEditBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.schedule.ScheduleAddActivity
import com.heechan.membeder.ui.schedule.ScheduleAddViewModel
import com.heechan.membeder.util.ExtraKey

class TodoFinalEditActivity : BaseActivity<ActivityTodoFinalEditBinding>(R.layout.activity_todo_final_edit) {
    val viewModel : TodoEditViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.singleton = SingletonObject

        val teamId = intent.getStringExtra(ExtraKey.TEAM_DATA.key)!!
        val ScheduleId = intent.getStringExtra(ExtraKey.SCHEDULE_DATA.key)!!

        binding.edtScheduleNameName.text


    }

}