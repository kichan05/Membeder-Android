package com.heechan.membeder.ui.team.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heechan.membeder.model.data.team.Team

class TeamDetailViewModel : ViewModel() {
    val teamData = MutableLiveData<Team>()
}