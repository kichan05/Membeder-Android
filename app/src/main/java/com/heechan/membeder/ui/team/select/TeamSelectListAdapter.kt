package com.heechan.membeder.ui.team.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.model.data.team.Team

class TeamSelectListAdapter(private val datas : List<Team>) : RecyclerView.Adapter<TeamSelectListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamSelectListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowTeamSelectListItemBinding.inflate(layoutInflater, parent, false)

        return TeamSelectListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamSelectListViewHolder, position: Int) {
        holder.onBind(datas[position], position)
    }

    override fun getItemCount(): Int = datas.size
}