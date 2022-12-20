package com.heechan.membeder.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentProfileBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.ChatRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.chatting.chat.ChatActivity
import com.heechan.membeder.ui.main.ProfileTeamListAdapter
import com.heechan.membeder.util.ExtraKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment(private val userData: User) :
    BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userData = userData
        binding.singleton = SingletonObject

        val teamList = userData.teamList
        val adapter = ProfileTeamListAdapter(teamList)
        binding.rvProfileTeam.adapter = adapter


        if (!userData.isMy() && userData.website.isNotEmpty()) {
            binding.btnProfileGotoSite.visibility = View.VISIBLE

            binding.btnProfileGotoSite.setOnClickListener(gotoUserSite)
        }

        if(!userData.isMy()) {
            binding.btnProfileGotoMessage.visibility = View.VISIBLE
            binding.btnProfileGotoMessage.setOnClickListener(gotoMessage)
        }
    }

    private val gotoUserSite = { v : View ->
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(userData.website)
        startActivity(intent)
    }

    private fun getChatRoomId() : String {
        val myRoomList = SingletonObject.userData.value!!.chatRoomList
        val userRoomList = userData.chatRoomList

        for (my in myRoomList){
            for (user in userRoomList){
                if(my == user) return my.id
            }
        }

//        val chatRepository = ChatRepositoryImpl()
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//        }


        return ""
    }

    private val gotoMessage = { v : View ->
        val intent = Intent(requireContext(), ChatActivity::class.java).apply {
            putExtra(ExtraKey.CHAT_ROOM_DATA.key, getChatRoomId())
        }

        requireContext().startActivity(intent)
    }
}