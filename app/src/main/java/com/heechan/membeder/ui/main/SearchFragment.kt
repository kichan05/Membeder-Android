package com.heechan.membeder.ui.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentSearchBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.common.adapter.team.TeamListAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listSearchTeamList.apply {
            adapter = TeamListAdapter(SingletonObject.userTeamList.value!!)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}