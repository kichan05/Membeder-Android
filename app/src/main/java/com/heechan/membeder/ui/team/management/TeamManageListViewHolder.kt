package com.heechan.membeder.ui.team.management

import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.ui.team.make.TeamMakeActivity

class TeamManageListViewHolder (private val view : RowTeamSelectListItemBinding) : RecyclerView.ViewHolder(view.root) {
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