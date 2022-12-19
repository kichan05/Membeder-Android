package com.heechan.membeder.ui.chatting.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.heechan.membeder.databinding.RowChatlistMyBinding
import com.heechan.membeder.databinding.RowChatlistOntherBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.util.ChatType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatListAdapter : ListAdapter<Chat, ChatListItemViewHolder>(ChatListDiffUtilCallBack()) {
    private val userDataList = mutableMapOf<String, User>()
//    private val chatDataList = mutableListOf<Chat>()

    class ChatListDiffUtilCallBack : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean =
            oldItem.message == newItem.message
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListItemViewHolder {
        if (viewType == ChatType.ME.ordinal) {
            val view = RowChatlistMyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return ChatMeViewHolder(view)
        } else {
            val view = RowChatlistOntherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return ChatOtherViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ChatListItemViewHolder, position: Int) {
        if (userDataList.containsKey(getItem(position).fromUserId)) {
            holder.onBind(getItem(position), userDataList[getItem(position).fromUserId]!!)
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            val repositoryImpl = AuthRepositoryImpl()
            val res = withContext(Dispatchers.IO) {
                repositoryImpl.getUserData(getItem(position).fromUserId)
            }

            if (res.isSuccessful && res.body() != null) {
                userDataList[getItem(position).fromUserId] = res.body()!!.user
                holder.onBind(getItem(position), userDataList[getItem(position).fromUserId]!!)
            } else {
                Log.e("[ChatAdapter]", res.errorBody().toString())
            }
        }
    }

//    fun clearItems() {
//        chatDataList.clear()
//    }
//
//    override fun getItem(position: Int): Chat {
//        return chatDataList[position]
//    }

    override fun getItemViewType(position: Int): Int {
        Log.d("[ChatType]", getItem(position).toString())
        return if (getItem(position).isMyChat()) {
            Log.d("[ChatType]", getItem(position).message + " 나")
            ChatType.ME.ordinal
        } else {
            Log.d("[ChatType]", getItem(position).message + " 너")
            ChatType.OTHER.ordinal
        }
    }
}