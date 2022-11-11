package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeBinding
import com.heechan.membeder.databinding.FragmentMoreBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.ui.splash.SplashActivity
import com.heechan.membeder.ui.splash.SplashViewModel

class MoreFragment : BaseFragment<FragmentMoreBinding>(R.layout.fragment_more) {
    val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMoreLogout.setOnClickListener {
            SingletonObject.apply {
                userData.value = null
                setToken("", requireContext())
            }

            val intent = Intent(context, SplashActivity::class.java)
            requireContext().startActivity(intent)
            (requireContext() as MainActivity).finish()
        }

    }
}