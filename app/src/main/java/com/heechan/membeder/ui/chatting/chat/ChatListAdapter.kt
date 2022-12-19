package com.heechan.membeder.ui.chatting.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.heechan.membeder.databinding.RowChatlistOntherBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatListAdapter : ListAdapter<Chat, ChatListViewHolder>(ChatListDiffUtilCallBack()) {
    val userDataList = mutableMapOf<String, User>()

    class ChatListDiffUtilCallBack : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean =
            oldItem.message == newItem.message
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val view = RowChatlistOntherBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ChatListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        if(userDataList.containsKey(getItem(position).fromUserId)) {
            holder.onBind(getItem(position), userDataList[getItem(position).fromUserId]!!)
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            val repositoryImpl = AuthRepositoryImpl()
            val res = withContext(Dispatchers.IO){
                repositoryImpl.getUserData(getItem(position).fromUserId)
            }

            if(res.isSuccessful && res.body() != null) {
                userDataList[getItem(position).fromUserId] = res.body()!!.user
                holder.onBind(getItem(position), userDataList[getItem(position).fromUserId]!!)
            }
            else {
                Log.e("[ChatAdapter]", res.errorBody().toString())
            }
        }
    }
}