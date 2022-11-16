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
import com.heechan.membeder.databinding.FragmentScheduleAccountBinding

class ScheduleAccountFragment : BaseFragment<FragmentScheduleAccountBinding>(R.layout.fragment_schedule_account) {
    val viewModel : ScheduleAddViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        binding.btnScheduleAccountNext.setOnClickListener {
            (activity as ScheduleAddActivity).gotoNext(1)
        }

        return binding.root
    }
}