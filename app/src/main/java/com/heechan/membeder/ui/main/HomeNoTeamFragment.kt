package com.heechan.membeder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeNoTeamBinding

class HomeNoTeamFragment : BaseFragment<FragmentHomeNoTeamBinding>(R.layout.fragment_home_no_team) {
    val viewModel : MainViewModel by activityViewModels()

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

        viewModel.getTeamList()

        viewModel.teamList.observe(viewLifecycleOwner) {
            val adapter = TeamBuildingListAdapter(
                if(it.teamList.size <= 3) it.teamList else it.teamList.subList(0, 3)
            )
            binding.listHomeNoTeamTeamRecommend.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        binding.btnHomeNoTeamGotoTeamBuilding.setOnClickListener {
//            (activity as MainActivity).gotoTeamBuilding()
        }
    }
}