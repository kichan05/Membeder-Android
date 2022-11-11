package com.heechan.membeder.ui.team.joinReq

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamJoinRequestListBinding
import com.heechan.membeder.databinding.SnackbarBadBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.view.snack.BadSnackBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.userAgent

class JoinReqListViewHolder(private val binding: RowTeamJoinRequestListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val repository = TeamRepositoryImpl()
    private val teamId = SingletonObject.userData!!.teamList[SingletonObject.selectTeamIndex].id

    fun onBind(userData: User, isOwner: Boolean) {
        binding.userData = userData
        binding.isOwner = isOwner
    }

    init {
        binding.txtUserRequestItemRefusal.setOnClickListener {
            //거절
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    repository.refusalJoinRequest(team_id = teamId, user_id = binding.userData!!.id)
                }

                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(binding.root.context, "거절되었습니다.", Toast.LENGTH_SHORT).show()
                    (binding.root.context as JoinRequestListActivity).finish()
                } else {
                    BadSnackBar.make(
                        binding.root,
                        "거절에 실패했어요",
                        "팀 가입 요청을 거절하는데 실패했어요. 잠시 뒤에 다시 시도해주세요",
                        700
                    ).show()
                }
            }
        }

        binding.txtUserRequestItemAccept.setOnClickListener {
            //수락
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO){
                    repository.addMember(team_id = teamId, user_id = binding.userData!!.id)
                }

                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(binding.root.context, "수락되었습니다", Toast.LENGTH_SHORT).show()
                    (binding.root.context as JoinRequestListActivity).finish()
                } else {
                    BadSnackBar.make(
                        binding.root,
                        "수락에 실패했어요",
                        "팀 가입 요청을 수락하는데 실패했어요. 잠시 뒤에 다시 시도해주세요",
                        700
                    ).show()
                }
            }
        }
    }
}