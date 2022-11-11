package com.heechan.membeder.ui.team.make

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentTeamNameBinding

class TeamNameFragment : BaseFragment<FragmentTeamNameBinding>(R.layout.fragment_team_name) {
    val viewModel: TeamMakeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.errTeamName.errorMessasge = it ?: ""
            if(it != null) binding.errTeamName.visibility = View.VISIBLE
        }


        binding.btnTeamMakeNameNext.setOnClickListener {
            if (viewModel.inputCheckName()) {
                viewModel.errorMessage.value = null
                (activity as TeamMakeActivity).gotoNext(1)
            }
        }

        return binding.root
    }
}