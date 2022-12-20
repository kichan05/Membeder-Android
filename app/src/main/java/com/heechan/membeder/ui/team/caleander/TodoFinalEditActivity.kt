package com.heechan.membeder.ui.team.caleander

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTodoFinalEditBinding
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.util.ExtraKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodoFinalEditActivity : BaseActivity<ActivityTodoFinalEditBinding>(R.layout.activity_todo_final_edit) {
    val viewModel : TodoEditFinalViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.singleton = SingletonObject
        val scheduleData = intent.getParcelableExtra<Schedule>(ExtraKey.SCHEDULE_DATA.key)!!
        Log.d("[Schedule]", scheduleData.toString())

        scheduleData.apply {
            viewModel.name.value = name
            viewModel.description.value = description

            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            viewModel.deadLine.value = LocalDate.parse(deadLine.split("T")[0], format)
        }

        binding.edtScheduleNameDate.setOnClickListener {
            val clickListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val date = LocalDate.of(year, month + 1, dayOfMonth)
                viewModel.deadLine.value = date
            }

            val now = LocalDate.now()
            val picker = DatePickerDialog(this, clickListener, now.year, now.monthValue - 1, now.dayOfMonth).apply {
                show()
            }
        }

        viewModel.deadLine.observe(this) {
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
            binding.edtScheduleNameDate.setText(viewModel.deadLine.value?.format(dateFormatter))
        }

        binding.btnTodofinalTodoEdit.setOnClickListener {
            binding.edtScheduleNameName.text.toString()
        }
    }

}