package com.heechan.membeder.ui.team.manage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamBanListBinding
import com.heechan.membeder.databinding.RowTeamJoinRequestListBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.ui.team.manage.MemberBanListViewHolder

class MemberBanAdater(val datas: List<User>, val isOwner : Boolean) : RecyclerView.Adapter<MemberBanListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberBanListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowTeamBanListBinding.inflate(layoutInflater, parent, false)
        return MemberBanListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberBanListViewHolder, position: Int) {
        holder.onBind(datas[position], isOwner)
    }

    override fun getItemCount(): Int = datas.size
}