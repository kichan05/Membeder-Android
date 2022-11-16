package com.heechan.membeder.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentScheduleNameBinding

class ScheduleNameFragment : BaseFragment<FragmentScheduleNameBinding>(R.layout.fragment_schedule_name) {
    val viewModel : ScheduleAddViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.errScheduleName.errorMessasge = it
                binding.errScheduleName.visibility = View.VISIBLE
            }
        }

        binding.btnScheduleNameNext.setOnClickListener {
            if(viewModel.inputCheckName()) {
                (activity as ScheduleAddActivity).gotoNext(0)
            }
        }
    }

}