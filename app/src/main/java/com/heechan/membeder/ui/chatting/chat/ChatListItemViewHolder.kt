package com.heechan.membeder.ui.chatting.chat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.chat.Chat

abstract class ChatListItemViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(chatData : Chat, userData : User)
}