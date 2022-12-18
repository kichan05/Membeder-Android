package com.heechan.membeder.ui.chatting.chat

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.model.data.chat.Chat

class ChatListAdapter : ListAdapter<Chat, RecyclerView.ViewHolder>(ChatListDiffUtilCallBack()) {
    class ChatListDiffUtilCallBack : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean =
            oldItem.message == newItem.message
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}