package com.heechan.membeder.ui.team.make

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentTeamNameBinding

class TeamNameFragment : BaseFragment<FragmentTeamNameBinding>(R.layout.fragment_team_name) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.txt.setOnClickListener {
            (activity as TeamMakeActivity).navController.navigate(R.id.action_teamNameFragment_to_teamDescriptionFragment)
        }

        return binding.root
    }
}