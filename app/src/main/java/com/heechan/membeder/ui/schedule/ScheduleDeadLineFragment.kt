package com.heechan.membeder.ui.schedule

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentScheduleDeadLineBinding
import com.heechan.membeder.databinding.FragmentScheduleNameBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ScheduleDeadLineFragment :
    BaseFragment<FragmentScheduleDeadLineBinding>(R.layout.fragment_schedule_dead_line) {
    val viewModel: ScheduleAddViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        binding.edtScheduleDeadLineDate.setOnClickListener {
            val clickListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val date = LocalDate.of(year, month + 1, dayOfMonth)
                viewModel.deadLine.value = date
            }

            val now = LocalDate.now()
            val picker = DatePickerDialog(requireContext(), clickListener, now.year, now.monthValue - 1, now.dayOfMonth).apply {
                show()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            if(it == null) {
                binding.errScheduleDeadLine.visibility = View.GONE
            } else {
                binding.errScheduleDeadLine.errorMessasge = it
                binding.errScheduleDeadLine.visibility = View.VISIBLE
            }
        }

        binding.btnScheduleDeadLineSubmit.setOnClickListener {
            if (viewModel.inputCheckDeadLine()) {
                viewModel.addSchedule()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.deadLine.observe(viewLifecycleOwner) {
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
            binding.edtScheduleDeadLineDate.setText(viewModel.deadLine.value?.format(dateFormatter))
        }
    }
}