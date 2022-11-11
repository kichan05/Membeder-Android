package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeTeamBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.ui.common.scheduleList.ScheduleListAdapter
import com.heechan.membeder.ui.schedule.ScheduleAddActivity
import com.heechan.membeder.ui.team.joinReq.JoinRequestListActivity
import com.heechan.membeder.ui.team.management.TeamSelectActivity

class HomeTeamFragment : BaseFragment<FragmentHomeTeamBinding>(R.layout.fragment_home_team) {
    val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        binding.txtHomeTeamSubTitle.setOnClickListener {
            val intent = Intent(requireContext(), JoinRequestListActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listSchedule.apply {
            adapter = ScheduleListAdapter(SingletonObject.userData.value!!.teamList[0].schedule)
            layoutManager = LinearLayoutManager(requireContext())
        }

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