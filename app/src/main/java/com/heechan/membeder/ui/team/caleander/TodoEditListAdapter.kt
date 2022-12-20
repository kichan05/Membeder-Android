package com.heechan.membeder.ui.team.caleander

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowScheduleList2Binding
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.util.ExtraKey

class TodoEditListAdapter(private val items: List<Schedule>) :
    RecyclerView.Adapter<TodoEditListAdapter.ScheduleListEditViewHolder>() {
    class ScheduleListEditViewHolder(private val view: RowScheduleList2Binding) :
        RecyclerView.ViewHolder(view.root) {

        fun onBind(schedule: Schedule) {
            view.scheduleData = schedule
        }

        init {
            view.root.setOnClickListener {
                val intent = Intent(view.root.context, TodoEditFinalActivity::class.java).apply {
                    putExtra(ExtraKey.SCHEDULE_DATA.key, view.scheduleData)
                }
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListEditViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowScheduleList2Binding.inflate(layoutInflater, parent, false)

        return ScheduleListEditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleListEditViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size
}