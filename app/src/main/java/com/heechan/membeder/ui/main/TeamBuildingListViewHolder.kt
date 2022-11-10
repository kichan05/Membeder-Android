package com.heechan.membeder.ui.main

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamBuildingListItemBinding
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.common.bindDateFormat
import com.heechan.membeder.ui.team.detail.TeamDetailActivity
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import com.heechan.membeder.util.ExtraKey
import kotlinx.coroutines.*

class TeamBuildingListViewHolder(private val view: RowTeamBuildingListItemBinding) :
    RecyclerView.ViewHolder(view.root) {
    lateinit var teamData: Team

    fun onBind(teamData: Team) {
        this.teamData = teamData
        view.teamData = teamData
    }

    init {
        view.root.setOnClickListener {
            with(it.context) {
                val intent = Intent(this, TeamDetailActivity::class.java).apply {
                    putExtra(ExtraKey.TEAM_DATA.key, teamData)
                }
                startActivity(intent)
            }
        }
    }
}