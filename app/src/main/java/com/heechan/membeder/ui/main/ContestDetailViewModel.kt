package com.heechan.membeder.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heechan.membeder.model.data.contest.ContestRes

class ContestDetailViewModel : ViewModel(){
    val contestData = MutableLiveData<ContestRes>()
    val contestName = MutableLiveData<String>()
    val contestPoster = MutableLiveData<String>()
    val contestUrl = MutableLiveData<String>()
    val contestHost = MutableLiveData<String>()
    val contestPrize = MutableLiveData<String>()
    val contestTarget = MutableLiveData<String>()

}