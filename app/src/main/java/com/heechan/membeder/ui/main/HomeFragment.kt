package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentHomeBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.chatting.chat.ChatActivity
import com.heechan.membeder.ui.login.LoginActivity
import com.heechan.membeder.ui.team.manage.MainTeamManageActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = requireFragmentManager()

        val tran = fragmentManager.beginTransaction()
        val fragment = if (SingletonObject.userData.value!!.teamList.isEmpty()) {
            HomeNoTeamFragment()
        } else {
            HomeTeamFragment()
        }

        tran.replace(R.id.fl_main, fragment)
        tran.commit()
        binding.headerHome.setMenu1IconClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            startActivity(intent)
        }
        binding.headerHome.setMenu2IconClickListener {
            val intent = Intent(context, MainTeamManageActivity::class.java)
            startActivity(intent)
        }
    }
}