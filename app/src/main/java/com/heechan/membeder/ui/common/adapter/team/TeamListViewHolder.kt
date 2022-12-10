package com.heechan.membeder.ui.common.adapter.team

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.ui.team.select.TeamSelectActivity

class TeamListViewHolder (private val view : RowTeamSelectListItemBinding) : RecyclerView.ViewHolder(view.root) {
    private var position : Int? = null

    fun onBind(teamData : Team, position : Int){
        view.teamData = teamData
        this.position = position
    }

    constructor(view : RowTeamSelectListItemBinding, clickListener : (View) -> Unit) : this(view){
        view.root.setOnClickListener(clickListener)
    }

    init {
        view.root.setOnClickListener {
            SingletonObject.apply {
                selectTeam.value = userData.value!!.teamList[position!!]
            }
            (it.context as TeamSelectActivity).finish()
        }
    }
}