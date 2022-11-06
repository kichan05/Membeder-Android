package com.heechan.membeder.ui.main

import androidx.lifecycle.ViewModel
import com.heechan.membeder.model.data.SingletonObject

class HomeViewModel : ViewModel() {
    val userData = SingletonObject.userData!!
}