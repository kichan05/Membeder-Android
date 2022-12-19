package com.heechan.membeder.ui.main

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamBuildingListItemBinding
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.ui.team.detail.TeamDetailActivity
import com.heechan.membeder.util.ExtraKey

class MoreTeamListViewHolder(private val view: RowTeamBuildingListItemBinding) :
    RecyclerView.ViewHolder(view.root) {
    lateinit var teamData: Team

    fun onBind(teamData: Team) {
        this.teamData = teamData
        view.teamData = teamData
    }

    init {
        view.root.setOnClickListener {
            val intent = Intent(view.root.context, TeamDetailActivity::class.java).apply {
                putExtra(ExtraKey.TEAM_DATA.key, teamData.id)
            }
            Log.d("TeamBuildingListViewHolder", "teamData : ${teamData.name}")
            view.root.context.startActivity(intent)
        }
    }
}