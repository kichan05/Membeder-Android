package com.heechan.membeder.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentContestBinding
import com.heechan.membeder.databinding.FragmentHomeBinding

class ContestFragment : BaseFragment<FragmentContestBinding>(R.layout.fragment_contest) {
    val viewModel : ContestViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        viewModel.getContestData()
        viewModel.contestList.observe(viewLifecycleOwner) {
            Log.d("컨테스트", it.toString())
            val adapter = ContestListAdapter(it)
            binding.rvContestMain.adapter = adapter
            adapter.notifyDataSetChanged()
            binding.rvContestMain.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}