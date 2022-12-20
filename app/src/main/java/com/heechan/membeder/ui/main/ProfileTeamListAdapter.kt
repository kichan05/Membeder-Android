package com.heechan.membeder.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamBuildingListItemBinding
import com.heechan.membeder.model.data.team.Team

class ProfileTeamListAdapter (private val datas : List<Team>) : RecyclerView.Adapter<MoreTeamListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreTeamListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowTeamBuildingListItemBinding.inflate(layoutInflater, parent, false)

        return MoreTeamListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MoreTeamListViewHolder, position: Int) {
        holder.onBind(datas[position])
    }
}