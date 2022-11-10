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
import com.heechan.membeder.ui.team.management.TeamSelectActivity

class HomeTeamFragment : BaseFragment<FragmentHomeTeamBinding>(R.layout.fragment_home_team) {
    val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel
        binding.teamData = SingletonObject.userData?.teamList!![SingletonObject.selectTeamIndex]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d("teadData", SingletonObject.userData!!.teamList[0].toString())
        binding.listSchedule.apply {
            adapter = ScheduleListAdapter(SingletonObject.userData!!.teamList[0].schedule)
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