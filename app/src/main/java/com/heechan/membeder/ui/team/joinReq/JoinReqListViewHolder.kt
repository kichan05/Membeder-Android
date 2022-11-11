package com.heechan.membeder.ui.team.joinReq

import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamJoinRequestListBinding
import com.heechan.membeder.model.data.auth.User

class JoinReqListViewHolder(private val binding : RowTeamJoinRequestListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData : User) {
        binding.userData = userData
    }

    init {
        binding.txtUserRequestItemRefusal.setOnClickListener {

        }
    }
}