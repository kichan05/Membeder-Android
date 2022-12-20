package com.heechan.membeder.ui.main

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowContestItemBinding
import com.heechan.membeder.model.data.contest.ContestRes
import com.heechan.membeder.util.ExtraKey

class ContestListViewHolder(private val binding : RowContestItemBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var contestData : ContestRes
    fun onBind(data : ContestRes) {
        this.contestData = data
        binding.contestName = data.name
        binding.contestParticipants =
            data.target.toString().replace("[","").replace("]","")
        binding.contestDeadline = data.receipt.substring(0 until 10)
    }

    init {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, ContestDetailActivity::class.java).apply {
                putExtra(ExtraKey.CONTEST_DATA_Name.key, contestData.name)
                putExtra(ExtraKey.CONTEST_DATA_Poster.key, contestData.poster)
                putExtra(ExtraKey.CONTEST_DATA_Url.key, contestData.content)
                putExtra(ExtraKey.CONTEST_DATA_Host.key, contestData.host)
                putExtra(ExtraKey.CONTEST_DATA_Prize.key, contestData.award)
                putExtra(ExtraKey.CONTEST_DATA_Target.key, contestData.target.toString().replace("[","").replace("]",""))
            }
            Log.d("ContestListViewHolder", "contestData : ${contestData.name}")
            binding.root.context.startActivity(intent)
        }
    }
}
