package com.heechan.membeder.ui.chatting.chat

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityChatBinding

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {
    val vm : ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm

        vm.roomId.value = "testRoom"
        vm.getChatData()
        binding.hdChat.title = vm.roomId.value!!

        binding.txtChatSend.setOnClickListener(sendMessage)
        binding.listChat.adapter = ChatListAdapter()

        vm.chatList.observe(this) {
            (binding.listChat.adapter as ChatListAdapter).submitList(it.reversed())
        }
    }

    private val sendMessage = { v : View ->
        vm.sendMessage()
    }
}