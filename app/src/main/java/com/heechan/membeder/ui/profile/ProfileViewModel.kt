package com.heechan.membeder.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {
    private val authRepository = AuthRepositoryImpl()

    val userId = MutableLiveData<String>()
    val userData = MutableLiveData<User>()

    fun getUserData(){
        viewModelScope.launch {
            val res = withContext(Dispatchers.IO){
                authRepository.getUserData(userId.value!!)
            }

            if(res.isSuccessful){
                userData.value = res.body()!!.user
            }
        }
    }
}