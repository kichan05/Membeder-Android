package com.heechan.membeder.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentProfileBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.chat.ChatRoomReq
import com.heechan.membeder.model.remote.ChatRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.chatting.chat.ChatActivity
import com.heechan.membeder.ui.main.ProfileTeamListAdapter
import com.heechan.membeder.util.ExtraKey
import kotlinx.coroutines.*


class ProfileFragment(private val userData: User) :
    BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val chatRepository = ChatRepositoryImpl()

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

        if (!userData.isMy()) {
            binding.btnProfileGotoMessage.visibility = View.VISIBLE
            binding.btnProfileGotoMessage.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    val intent = Intent(requireContext(), ChatActivity::class.java)
                    val roomId = withContext(Dispatchers.Default) {
                        getChatId()
                    }
                    intent.putExtra(ExtraKey.CHAT_ROOM_DATA.key, roomId)
                    requireContext().startActivity(intent)
                }
            }
        }
    }

    private val gotoUserSite = { v: View ->
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(userData.website)
        startActivity(intent)
    }

    private suspend fun createChatRoom(): String {
        val createTeamReq = ChatRoomReq(
            name = "${SingletonObject.userData.value!!.name} - ${userData.name} 개인"
        )

        val res = chatRepository.addRoom(createTeamReq)
        return res.body()!!.id
    }

    private suspend fun getChatId(): String {
        val myRoomList = SingletonObject.userData.value!!.chatRoomList
        val userRoomList = userData.chatRoomList
        for (my in myRoomList) {
            for (user in userRoomList) {
                if (my == user) {
                    return my.id
                }
            }
        }

        return createChatRoom()
    }
}