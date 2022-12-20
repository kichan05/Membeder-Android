package com.heechan.membeder.ui.chatting.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowChatRoomBinding
import com.heechan.membeder.model.data.chat.ChatRoom

class ChatRoomListAdapter(private val items : List<ChatRoom>) : RecyclerView.Adapter<ChatRoomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        val view = RowChatRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ChatRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size
}