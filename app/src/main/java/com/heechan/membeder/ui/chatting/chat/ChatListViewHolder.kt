package com.heechan.membeder.ui.chatting.chat

import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowChatlistOntherBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.chat.Chat
import java.text.DateFormat
import java.text.SimpleDateFormat

class ChatListViewHolder(private val binding : RowChatlistOntherBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(chatData : Chat, userData : User) {
        binding.chatData = chatData
        binding.timeStamp = SimpleDateFormat("hh:mm").format(chatData.timestamp)
        binding.userData = userData
    }
}