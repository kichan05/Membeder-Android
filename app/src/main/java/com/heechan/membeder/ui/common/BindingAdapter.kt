package com.heechan.membeder.ui.common

import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.ui.common.scheduleList.ScheduleListAdapter
import com.heechan.membeder.util.State
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("bindVisibility")
fun bindVisibility(view : View, isVisible : Boolean) {
    view.visibility = if(isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("bindRadioButtonValue")
fun bindRadioButtonValue(rb : RadioButton, value : Int) {
    rb.isChecked = rb.id == value
}

@BindingAdapter("bindTeamScheduleList")
fun bindTeamScheduleList(view: RecyclerView, scheduleList: List<Schedule>) {
    Log.d("bindTeamScheduleList", scheduleList.toString())
    view.apply {
        adapter = ScheduleListAdapter(scheduleList)
        adapter!!.notifyDataSetChanged()
    }
}

@BindingAdapter("bindSwipeOnRefresh")
fun bindSwipeOnRefresh(swiperRefresh : SwipeRefreshLayout, job : () -> Unit){
    swiperRefresh.setOnRefreshListener(job)
}

@BindingAdapter("bindSwipeRefreshing")
fun bindSwipeRefreshing(swipeRefresh: SwipeRefreshLayout, status: State?) {
    if (status != null) swipeRefresh.isRefreshing = (status == State.LOADING)
}

