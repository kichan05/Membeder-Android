package com.heechan.membeder.ui.main

import android.os.Bundle
import android.view.View
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeBinding
import com.heechan.membeder.model.data.SingletonObject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = requireFragmentManager()

        val tran = fragmentManager.beginTransaction()
        val fragment = if (SingletonObject.userData!!.teamList.isEmpty()) {
            HomeNoTeamFragment()
        } else {
            HomeTeamFragment()
        }

        tran.replace(R.id.fl_main, fragment)
        tran.commit()
    }
}