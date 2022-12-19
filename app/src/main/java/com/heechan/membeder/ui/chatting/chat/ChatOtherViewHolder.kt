package com.heechan.membeder.ui.chatting.chat

import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowChatlistMyBinding
import com.heechan.membeder.databinding.RowChatlistOntherBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.chat.Chat
import java.text.SimpleDateFormat

class ChatOtherViewHolder(private val binding : RowChatlistOntherBinding): ChatListItemViewHolder(binding.root) {
    override fun onBind(chatData : Chat, userData : User) {
        binding.chatData = chatData
        binding.timeStamp = SimpleDateFormat("hh:mm").format(chatData.timestamp)
        binding.userData = userData
    }
}

class ChatMeViewHolder(private val binding : RowChatlistMyBinding): ChatListItemViewHolder(binding.root) {
    override fun onBind(chatData : Chat, userData : User) {
        binding.chatData = chatData
        binding.timeStamp = SimpleDateFormat("hh:mm").format(chatData.timestamp)
        binding.userData = userData
    }
}