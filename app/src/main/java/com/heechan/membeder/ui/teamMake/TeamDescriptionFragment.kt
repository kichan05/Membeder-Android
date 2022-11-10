package com.heechan.membeder.ui.teamMake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentTeamDescriptionBinding
import com.heechan.membeder.databinding.FragmentTeamNameBinding

class TeamDescriptionFragment : BaseFragment<FragmentTeamDescriptionBinding>(R.layout.fragment_team_description) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }
}