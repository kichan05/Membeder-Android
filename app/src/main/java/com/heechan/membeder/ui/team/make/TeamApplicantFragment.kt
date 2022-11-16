package com.heechan.membeder.ui.team.make

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentTeamApplicantBinding
import com.heechan.membeder.databinding.FragmentTeamNameBinding

class TeamApplicantFragment :
    BaseFragment<FragmentTeamApplicantBinding>(R.layout.fragment_team_applicant) {
    val viewModel : TeamMakeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        binding.btnTeamApplicantNext.setOnClickListener {
            (activity as TeamMakeActivity).gotoNext(3)
        }

        return binding.root
    }
}