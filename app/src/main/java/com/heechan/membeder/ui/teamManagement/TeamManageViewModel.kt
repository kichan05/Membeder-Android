package com.heechan.membeder.ui.teamManagement

import androidx.lifecycle.ViewModel
import com.heechan.membeder.model.remote.TeamRepositoryImpl

class TeamManageViewModel:ViewModel() {
    private val team = TeamRepositoryImpl()
}