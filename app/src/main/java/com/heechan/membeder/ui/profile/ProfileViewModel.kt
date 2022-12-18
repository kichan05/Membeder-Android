package com.heechan.membeder.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heechan.membeder.model.data.auth.User

class ProfileViewModel : ViewModel() {
    val user = MutableLiveData<User>()
}