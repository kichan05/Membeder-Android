package com.heechan.membeder.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentProfileBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.main.ProfileTeamListAdapter

class ProfileFragment(private val userData : User) : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userData = userData
        binding.singleton = SingletonObject

        val teamList = userData.teamList
        val adapter = ProfileTeamListAdapter(teamList)
        binding.rvProfileTeam.adapter = adapter
    }
}