package com.heechan.membeder.ui.team.manage

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamBanListBinding
import com.heechan.membeder.databinding.RowTeamJoinRequestListBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.team.joinReq.JoinRequestListActivity
import com.heechan.membeder.ui.view.snack.BadSnackBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemberBanListViewHolder(private val binding: RowTeamBanListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val repository = TeamRepositoryImpl()
    private val teamId = SingletonObject.selectTeam.value!!.id

    fun onBind(userData: User, isOwner: Boolean) {
        binding.userData = userData
        binding.isOwner = isOwner
    }

    init {
        binding.txtUserRequestItemRefusal.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    repository.BanMember(team_id = teamId, user_id = binding.userData!!.id)
                }
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(binding.root.context, "추방되었습니다.", Toast.LENGTH_SHORT).show()
                    (binding.root.context as MemberBanActivity).finish()
                } else {
                    BadSnackBar.make(
                        binding.root,
                        "추방 실패했어요",
                        "팀원을 추방하는데 실패했어요. 잠시 뒤에 다시 시도해주세요",
                        700
                    ).show()
                }
            }
        }
    }
}