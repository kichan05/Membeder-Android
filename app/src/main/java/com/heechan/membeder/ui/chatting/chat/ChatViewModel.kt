package com.heechan.membeder.ui.chatting.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.model.remote.ChatRepositoryImpl

class ChatViewModel : ViewModel() {
    private val chatRepository = ChatRepositoryImpl()

    val roomId = MutableLiveData<String>()
    val chatList = MutableLiveData<List<Chat>>()

    fun getChatData() {
        chatRepository.getChatList(roomId.value!!).addSnapshotListener { value, e ->
            if(e != null){
                return@addSnapshotListener
            }

            if (value != null) {
                val chatList = mutableListOf<Chat>()
                for (document in value) {
                    val chat = document.toObject(Chat::class.java)
                    chatList.add(chat)
                }
                this.chatList.value = chatList
                Log.d("[Chat]", chatList.toString())
            } else {
                Log.e("[Chat]", "Current data: null")
            }
        }
    }
}