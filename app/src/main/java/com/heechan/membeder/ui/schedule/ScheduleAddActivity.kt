package com.heechan.membeder.ui.schedule

import android.os.Bundle
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityScheduleAddBinding

class ScheduleAddActivity : BaseActivity<ActivityScheduleAddBinding>(R.layout.activity_schedule_add) {
    private val viewModel : ScheduleAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}