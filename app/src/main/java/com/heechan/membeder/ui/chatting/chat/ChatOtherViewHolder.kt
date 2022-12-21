package com.heechan.membeder.ui.chatting.chat

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowChatlistMyBinding
import com.heechan.membeder.databinding.RowChatlistOntherBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.ui.profile.ProfileActivity
import com.heechan.membeder.util.ExtraKey
import java.text.SimpleDateFormat

class ChatOtherViewHolder(private val binding : RowChatlistOntherBinding): ChatListItemViewHolder(binding.root) {
    override fun onBind(chatData : Chat, userData : User) {
        binding.chatData = chatData
        binding.timeStamp = SimpleDateFormat("hh:mm").format(chatData.timestamp)
        binding.userData = userData
    }

    init {
        binding.imgChatListOtherProfileImage.setOnClickListener {
            val intent = Intent(it.context, ProfileActivity::class.java).apply {
                putExtra(ExtraKey.USER_DATA.key, binding.userData!!.id)
            }
            it.context.startActivity(intent)
        }
    }
}

class ChatMeViewHolder(private val binding : RowChatlistMyBinding): ChatListItemViewHolder(binding.root) {
    override fun onBind(chatData : Chat, userData : User) {
        binding.chatData = chatData
        binding.timeStamp = SimpleDateFormat("hh:mm").format(chatData.timestamp)
        binding.userData = userData
    }
}