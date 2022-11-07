package com.heechan.membeder.ui.teamManagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.R
import com.heechan.membeder.model.data.team.Team

class TeamManageAdapter (private val datas : List<Team>) : RecyclerView.Adapter<TeamManageListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamManageListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_team_manage_list_item, parent, false)
        return TeamManageListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamManageListViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}