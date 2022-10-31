package com.heechan.membeder.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeBinding
import com.heechan.membeder.databinding.FragmentSearchBinding
import com.heechan.membeder.databinding.FragmentTeamBuildingBinding

class TeamBuildingFragment : BaseFragment<FragmentTeamBuildingBinding>(R.layout.fragment_team_building) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hdTeamBuilding.setActionButton("실행") {
            Toast.makeText(context, "ㅇㅁ너ㅏ엄ㄴㅇ", Toast.LENGTH_SHORT).show()
        }
    }
}