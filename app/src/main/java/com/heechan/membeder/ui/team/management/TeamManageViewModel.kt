package com.heechan.membeder.ui.team.management

import androidx.lifecycle.ViewModel
import com.heechan.membeder.model.remote.TeamRepositoryImpl

class TeamManageViewModel:ViewModel() {
    private val team = TeamRepositoryImpl()
}