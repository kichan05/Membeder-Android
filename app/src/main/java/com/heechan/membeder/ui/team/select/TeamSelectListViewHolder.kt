package com.heechan.membeder.ui.team.select

import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.ui.SingletonObject

class TeamSelectListViewHolder (private val view : RowTeamSelectListItemBinding) : RecyclerView.ViewHolder(view.root) {
    private var position : Int? = null

    fun onBind(teamData : Team, position : Int){
        view.teamData = teamData
        this.position = position
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