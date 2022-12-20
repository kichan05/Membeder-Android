package com.heechan.membeder.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowContestItemBinding
import com.heechan.membeder.model.data.contest.ContestRes

class ContestListAdapter(private val datas : List<ContestRes>) : RecyclerView.Adapter<ContestListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowContestItemBinding.inflate(layoutInflater,parent,false)
        return ContestListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContestListViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

}