package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeBinding
import com.heechan.membeder.ui.teamMake.TeamMakeActivity
import kotlinx.coroutines.flow.combineTransform

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHomeTeamTest.setOnClickListener {
            val intent = Intent(requireContext(), TeamMakeActivity::class.java)
            startActivity(intent)
        }

        binding.headerHome.setMenu1IconClickListener {
            Toast.makeText(context, "Hello Wortld", Toast.LENGTH_SHORT).show()
        }

    }
}