package com.heechan.membeder.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamBuildingListItemBinding
import com.heechan.membeder.model.data.team.Team

class TeamBuildingListAdapter (private val datas : List<Team>) : RecyclerView.Adapter<TeamBuildingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamBuildingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowTeamBuildingListItemBinding.inflate(layoutInflater, parent, false)

        return TeamBuildingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamBuildingListViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}