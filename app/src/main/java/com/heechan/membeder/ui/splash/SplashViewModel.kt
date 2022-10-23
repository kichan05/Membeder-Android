package com.heechan.membeder.ui.splash

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.heechan.membeder.util.DataStoreUtil

class SplashViewModel(application: Application) : ViewModel() {
    private val dataStore = DataStoreUtil(application)

    val accessToken = dataStore.accessToken.asLiveData()
}