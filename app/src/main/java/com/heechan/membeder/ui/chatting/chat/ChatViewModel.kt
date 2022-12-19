package com.heechan.membeder.ui.chatting.chat

import android.util.Log
import androidx.lifecycle.*
import com.heechan.membeder.model.data.chat.Chat
import com.heechan.membeder.model.remote.ChatRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel : ViewModel() {
    private val chatRepository = ChatRepositoryImpl()

    val roomId = MutableLiveData<String>()
    val chatList = MutableLiveData<List<Chat>>()

    val inputMessage = MutableLiveData<String>()

    fun getChatData() {
        chatRepository.getChatList(roomId.value!!).addSnapshotListener { value, e ->
            if (e != null) {
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

    fun sendMessage() {
        val chatReq = Chat(
            message = inputMessage.value!!,
            fromUserId = SingletonObject.userData.value!!.id,
            toRoomId = roomId.value!!
        )
        
        viewModelScope.launch {
            val chatRes = withContext(Dispatchers.IO) {
                chatRepository.sendMessage(chatReq)
            }

            if(chatRes == State.SUCCESS) {
                inputMessage.value = ""
            }

        }
        
    }
}