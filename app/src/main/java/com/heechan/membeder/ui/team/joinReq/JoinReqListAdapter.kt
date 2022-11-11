package com.heechan.membeder.ui.team.joinReq

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamJoinRequestListBinding
import com.heechan.membeder.model.data.auth.User

class JoinReqListAdapter(val datas: List<User>) : RecyclerView.Adapter<JoinReqListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoinReqListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowTeamJoinRequestListBinding.inflate(layoutInflater, parent, false)
        return JoinReqListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JoinReqListViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}