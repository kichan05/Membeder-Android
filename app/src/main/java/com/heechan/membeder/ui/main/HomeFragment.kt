package com.heechan.membeder.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeBinding
import com.heechan.membeder.ui.SingletonObject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.singleton = SingletonObject
        homeFragment()
//        SingletonObject.userTeamList.observe(viewLifecycleOwner) {
//            Toast.makeText(requireContext(), "팀리스트 변경됨", Toast.LENGTH_SHORT).show()
//            homeFragment()
//        }
    }

    private fun homeFragment() {
        Log.d("HomeFragment", SingletonObject.userTeamList.value.toString())
//        Log.d("HomeFragment", SingletonObject.userTeamList.size.toString())

        val fragmentManager = requireFragmentManager()

        val tran = fragmentManager.beginTransaction()
        val fragment = if (SingletonObject.userTeamList.value?.isEmpty() ?: false) {
            Log.d("HomeFragment", "팀리스트가 비어있음")
            HomeNoTeamFragment()
        } else {
            Log.d("HomeFragment", "팀리스트가 비어있지 않음")
            HomeTeamFragment()
        }

        tran.replace(R.id.fl_main, fragment)
        tran.commit()
    }
}