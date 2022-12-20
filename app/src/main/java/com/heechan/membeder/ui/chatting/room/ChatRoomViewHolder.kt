package com.heechan.membeder.ui.chatting.room

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowChatRoomBinding
import com.heechan.membeder.model.data.chat.ChatRoom
import com.heechan.membeder.ui.chatting.chat.ChatActivity
import com.heechan.membeder.util.ExtraKey

class ChatRoomViewHolder(private val binding : RowChatRoomBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var chatRoomID : String

    fun onBind(chatRoomID : String, chatRoomName : String) {
        binding.chatRoomName = chatRoomName
        this.chatRoomID = chatRoomID
    }

    init {
        binding.root.setOnClickListener {
            val intent = Intent(it.context, ChatActivity::class.java).apply {
                putExtra(ExtraKey.CHAT_ROOM_DATA.key, chatRoomID)
            }
            it.context.startActivity(intent)
        }
    }
}