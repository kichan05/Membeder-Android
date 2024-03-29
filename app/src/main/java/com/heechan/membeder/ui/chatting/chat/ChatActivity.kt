package com.heechan.membeder.ui.chatting.chat

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityChatBinding
import com.heechan.membeder.util.ExtraKey

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {
    val vm: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
        vm.roomId.value = intent.getStringExtra(ExtraKey.CHAT_ROOM_DATA.key)
        vm.getChatData()
        vm.getRoomData()

        binding.hdChat.setNavigationClickListener { finish() }

        binding.txtChatSend.setOnClickListener(sendMessage)
        vm.roomData.observe(this) {
            binding.hdChat.title = it.name
        }

        binding.listChat.adapter = ChatListAdapter()
        vm.chatList.observe(this) {
            (binding.listChat).apply {
                (adapter as ChatListAdapter).submitList(it)
                smoothScrollToPosition(it.size)
            }
        }
    }

    private val sendMessage = { v: View ->
        vm.sendMessage()
    }
}