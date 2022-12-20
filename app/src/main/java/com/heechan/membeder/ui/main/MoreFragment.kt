package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.ShareCompat.IntentReader
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentMoreBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.profile.ProfileActivity
import com.heechan.membeder.ui.splash.SplashActivity
import com.heechan.membeder.util.ExtraKey

class MoreFragment : BaseFragment<FragmentMoreBinding>(R.layout.fragment_more) {
    val viewModel: MoreViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        //val teamList = SingletonObject.userData.value!!.teamList
        viewModel.getTeamData()
        binding.singleton = SingletonObject

        viewModel.teamList.observe(viewLifecycleOwner) {
//            val adapter = MoreTeamListAdapter(it.teamList)
            val adapter = MoreTeamListAdapter(SingletonObject.userData.value!!.teamList)
            binding.rvMoreTeam.adapter = adapter
            adapter.notifyDataSetChanged()
            binding.rvMoreTeam.layoutManager = LinearLayoutManager(requireContext())
        }
        Log.d("사진", SingletonObject.userData.value!!.profileImg)
        Glide.with(this)
            .load(viewModel.userData.value!!.profileImg)
            .into(binding.ivMoreProfile)
    }
}