package com.heechan.membeder.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentScheduleDeadLineBinding
import com.heechan.membeder.databinding.FragmentScheduleNameBinding

class ScheduleDeadLineFragment : BaseFragment<FragmentScheduleDeadLineBinding>(R.layout.fragment_schedule_dead_line) {
    val viewModel : ScheduleAddViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        binding.btnScheduleDeadLineSubmit.setOnClickListener {
            viewModel.addSchedule()
        }

        return binding.root
    }
}