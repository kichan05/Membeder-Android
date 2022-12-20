package com.heechan.membeder.ui.team.caleander

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowScheduleList2Binding
import com.heechan.membeder.databinding.RowScheduleList3Binding
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import com.heechan.membeder.util.ExtraKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoDeleteListAdapter(private val items: List<Schedule>) :
    RecyclerView.Adapter<TodoDeleteListAdapter.ScheduleListDeleteViewHolder>() {
    class ScheduleListDeleteViewHolder(private val view: RowScheduleList3Binding) :
        RecyclerView.ViewHolder(view.root) {
        private val repository = TeamRepositoryImpl()
        private val teamId = SingletonObject.selectTeam.value!!.id
        fun onBind(schedule: Schedule) {
            view.scheduleData = schedule
        }
        init {
            view.TodoDeleteRecyclercView.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        Log.d("Dddddd",teamId)
                        Log.d("Dddddd",view.scheduleData!!.id)
                        repository.TodoDelete(team_id = teamId, schedule_id = view.scheduleData!!.id)
                    }
                    (view.root.context as TodoDeleteActivity).finish()

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListDeleteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowScheduleList3Binding.inflate(layoutInflater, parent, false)

        return ScheduleListDeleteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleListDeleteViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size
}