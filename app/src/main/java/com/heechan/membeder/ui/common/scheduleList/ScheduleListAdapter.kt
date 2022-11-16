package com.heechan.membeder.ui.common.scheduleList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.R
import com.heechan.membeder.databinding.RowScheduleListBinding
import com.heechan.membeder.model.data.schedule.Schedule

class ScheduleListAdapter(private val items: List<Schedule>) :
    RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder>() {
    class ScheduleListViewHolder(private val view: RowScheduleListBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(schedule: Schedule) {
            view.scheduleData = schedule
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowScheduleListBinding.inflate(layoutInflater, parent, false)

        return ScheduleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size
}