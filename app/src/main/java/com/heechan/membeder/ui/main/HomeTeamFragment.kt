package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeTeamBinding
import com.heechan.membeder.ui.schedule.ScheduleAddActivity
import com.heechan.membeder.ui.teamManagement.TeamSelectActivity

class HomeTeamFragment : BaseFragment<FragmentHomeTeamBinding>(R.layout.fragment_home_team) {
    val viewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgHomeTeamGotoTeamSelect.setOnClickListener {
            val intent = Intent(activity, TeamSelectActivity::class.java)
            startActivity(intent)
        }

        binding.btnHomeTeamAddSchedule.setOnClickListener {
            val intent = Intent(activity, ScheduleAddActivity::class.java)
            startActivity(intent)
        }
    }
}