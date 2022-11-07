package com.heechan.membeder.ui.teamManagement

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.R
import com.heechan.membeder.model.data.team.Team

class TeamManageListViewHolder (private val row : View) : RecyclerView.ViewHolder(row) {
    val teamlogo : ImageView = row.findViewById(R.id.img_teammanageImage_logo)
    val teamtitle: TextView = row.findViewById(R.id.txt_teammanagename_title)

    fun onBind(teamlist : Team){
        teamtitle.text = teamlist.name


        row.setOnClickListener {

        }
    }
}