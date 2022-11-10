package com.heechan.membeder.ui.team.management

import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.model.data.team.Team

class TeamManageListViewHolder (private val view : RowTeamSelectListItemBinding) : RecyclerView.ViewHolder(view.root) {
//    val teamlogo : ImageView = row.findViewById(R.id.img_teamSelect_teamLogo)
//    val teamtitle: TextView = row.findViewById(R.id.txt_teamSelect_teamName)

    fun onBind(teamData : Team){
        view.teamData = teamData
    }
}