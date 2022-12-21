package com.heechan.membeder.ui.common.scheduleList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.R
import com.heechan.membeder.databinding.RowScheduleList2Binding
import com.heechan.membeder.databinding.RowScheduleListBinding
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.schedule.TodoEditReq
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.team.caleander.TodoDeleteActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleListAdapter(private val items: List<Schedule>) :
    RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder>() {
    class ScheduleListViewHolder(private val view: RowScheduleListBinding) :
        RecyclerView.ViewHolder(view.root) {
        private val repository = TeamRepositoryImpl()
        private val teamId = SingletonObject.selectTeam.value!!.id
        fun onBind(schedule: Schedule) {
            view.scheduleData = schedule
        }
        init {
            view.TodoListRecyclerView.setOnClickListener {
                view.ckbScheduleItemIsComplete.isChecked = !view.ckbScheduleItemIsComplete.isChecked
                CoroutineScope(Dispatchers.Main).launch {
                    val response = withContext(Dispatchers.IO) {
                        val request = TodoEditReq(
                            name = view.scheduleData!!.name.toString(),
                            description = view.scheduleData!!.description.toString(),
                            complete = view.ckbScheduleItemIsComplete.isChecked.toString().toBoolean(),
                            deadLine = view.scheduleData!!.deadLine.toString()
                        )
                        repository.EditSchedule(
                            team_id = teamId, schedule_id = view.scheduleData!!.id,scheduleData = request
                        )
                    }

                }
            }
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