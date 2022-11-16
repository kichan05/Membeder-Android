package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentTeamBuildingBinding
import com.heechan.membeder.ui.team.make.TeamMakeActivity

class TeamBuildingFragment : BaseFragment<FragmentTeamBuildingBinding>(R.layout.fragment_team_building) {
    val viewModel : MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel


        viewModel.getTeamData()

        binding.hdTeamBuilding.setMenu1IconClickListener {
            val intent = Intent(requireContext(), TeamMakeActivity::class.java)
            startActivity(intent)
        }

        viewModel.teamList.observe(viewLifecycleOwner) {
            val adapter = TeamBuildingListAdapter(it.teamList)
            binding.listTeamBuildingTeamList.adapter = adapter
            adapter.notifyDataSetChanged()

            binding.listTeamBuildingTeamList.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}