package com.heechan.membeder.ui.team.caleander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTodoEditBinding
import com.heechan.membeder.databinding.ActivityTodoFinalEditBinding
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.schedule.ScheduleAddActivity
import com.heechan.membeder.ui.schedule.ScheduleAddViewModel
import com.heechan.membeder.util.ExtraKey
import okhttp3.internal.notifyAll

class TodoFinalEditActivity : BaseActivity<ActivityTodoFinalEditBinding>(R.layout.activity_todo_final_edit) {
    val viewModel : TodoEditFinalViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.singleton = SingletonObject
        val schedulename = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_NAME.key)!!
        val scheduleDescribtion = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_DESCRIBTION.key)!!
        val scheduledeadline = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_DEADLINE.key)!!
        val schedulecompleate = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_COMPLETE.key)!!

        binding.edtScheduleNameName.setText(schedulename)
        binding.edtScheduleNameDecription.setText(scheduleDescribtion)
        binding.edtScheduleNameDate.setText(scheduledeadline)

        binding.btnTodofinalTodoEdit.setOnClickListener {

            binding.edtScheduleNameName.text.toString()


        }




    }

}