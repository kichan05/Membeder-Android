package com.heechan.membeder.ui.chatting.room

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.remote.ChatRepositoryImpl
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ChatRoomViewModel : ViewModel() {
    private val chatRepository = ChatRepositoryImpl()
}