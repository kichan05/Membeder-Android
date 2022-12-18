package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ShareCompat.IntentReader
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentMoreBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.profile.ProfileActivity
import com.heechan.membeder.ui.splash.SplashActivity
import com.heechan.membeder.util.ExtraKey

class MoreFragment : BaseFragment<FragmentMoreBinding>(R.layout.fragment_more) {
    val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.singleton = SingletonObject

        binding.btnMoreLogout.setOnClickListener(logout)
        binding.imgMoreGotoProfile.setOnClickListener(gotoProfile)
    }

    private val logout = { _: View ->
        SingletonObject.apply {
            userData.value = null
            setToken("", requireContext())
        }

        val intent = Intent(context, SplashActivity::class.java)
        requireContext().startActivity(intent)
        (requireContext() as MainActivity).finish()
    }

    private val gotoProfile = gotoProfile@{ _: View ->
        val intent = Intent(requireContext(), ProfileActivity::class.java).apply {
            putExtra(ExtraKey.USER_DATA.key, SingletonObject.userData.value!!.id)
        }
        startActivity(intent)
    }
}