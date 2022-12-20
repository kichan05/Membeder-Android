package com.heechan.membeder.ui.chatting.room

import android.os.Bundle
import android.util.Log
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityChatRoomBinding
import com.heechan.membeder.ui.SingletonObject

class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding>(R.layout.activity_chat_room) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.hdChatRoom.setNavigationClickListener { finish() }

        binding.listChatRoom.apply {
//            adapter = ChatRoomListAdapter(SingletonObject.userData.value!!.chatRoomList)
            adapter = ChatRoomListAdapter(SingletonObject.getChatRoomList())
            Log.d("[ChatRoom] count", SingletonObject.getChatRoomList().size.toString())
        }
    }
}