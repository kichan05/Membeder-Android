package com.heechan.membeder.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowContestItemBinding
import com.heechan.membeder.model.data.contest.ContestRes

class ContestListViewHolder(private val binding : RowContestItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data : ContestRes) {
        binding.contestName = data.name
        binding.contestParticipants =
            data.target.toString().replace("[","").replace("]","")
        binding.contestDeadline = data.receipt.substring(0 until 10)
    }
}
