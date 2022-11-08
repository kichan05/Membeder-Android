package com.heechan.membeder.ui.teamManagement

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.R
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.model.data.team.Team

class TeamManageAdapter (private val datas : List<Team>) : RecyclerView.Adapter<TeamManageListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamManageListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowTeamSelectListItemBinding.inflate(layoutInflater, parent, false)

        return TeamManageListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamManageListViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}