package com.heechan.membeder.ui.chatting.room

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowChatRoomBinding
import com.heechan.membeder.model.data.chat.ChatRoom
import com.heechan.membeder.ui.chatting.chat.ChatActivity
import com.heechan.membeder.util.ExtraKey

class ChatRoomViewHolder(private val binding : RowChatRoomBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(chatRoomData : ChatRoom) {
        binding.chatRoom = chatRoomData
    }

    init {
        binding.root.setOnClickListener {
            val intent = Intent(it.context, ChatActivity::class.java).apply {
                putExtra(ExtraKey.CHAT_ROOM_DATA.key, binding.chatRoom?.id)
            }
        }
    }
}