package com.heechan.membeder.ui.common.adapter.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.model.data.team.Team

class TeamListAdapter (private val datas : List<Team>) : RecyclerView.Adapter<TeamListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowTeamSelectListItemBinding.inflate(layoutInflater, parent, false)

        return TeamListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.onBind(datas[position], position)
    }

    override fun getItemCount(): Int = datas.size
}