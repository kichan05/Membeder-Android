package com.heechan.membeder.ui.team.manage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.AuthoritymanageItemBinding
import com.heechan.membeder.model.data.auth.User

class AuthorityManageListAdapter(private val datas : List<User>, val isOwner : Boolean, val isChecked : List<Boolean>) : RecyclerView.Adapter<AuthorityManageListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorityManageListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AuthoritymanageItemBinding.inflate(layoutInflater,parent,false)
        return AuthorityManageListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AuthorityManageListViewHolder, position: Int) {
        holder.onBind(datas[position],isOwner, isChecked[position])
    }

    override fun getItemCount(): Int = datas.size
}