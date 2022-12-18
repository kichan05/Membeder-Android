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

        binding.txtChatSend.setOnClickListener(sendMessage)
    }

    private val sendMessage = { v : View ->
        vm.sendMessage()
    }
}

/*
* 채팅을 어떻게 만들어야 할까
* 1. 채팅방 id를 가여온다.
* 2. 해당 방의 채팅 목록을 불러온다.
* 3. 스내샷을 걸어 준다.
* */