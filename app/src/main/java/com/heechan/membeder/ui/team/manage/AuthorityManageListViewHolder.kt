package com.heechan.membeder.ui.team.manage

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.AuthoritymanageItemBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.TeamPermissionRepositoryImpl
import com.heechan.membeder.model.remote.TeamRepository
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.view.snack.BadSnackBar
import kotlinx.coroutines.*

class AuthorityManageListViewHolder(private val binding : AuthoritymanageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val teamPermissionRepository = TeamPermissionRepositoryImpl()
    private val teamRepository = TeamRepositoryImpl()
    private val teamId = SingletonObject.selectTeam.value!!.id
    private val userId = SingletonObject.userData.value!!.id

    fun onBind(userData : User, isOwner : Boolean, isChecked : Boolean){
        binding.userData = userData
        binding.isOwner = isOwner
        binding.isChecked = isChecked
    }
    init {
        binding.swItemOnOff.setOnCheckedChangeListener { compoundButton, ischecked ->
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    if(ischecked)
                    {
                        teamPermissionRepository.deletePermission(teamData = teamId, userData = binding.userData!!.id)
                    }
                    else
                    {
                        teamPermissionRepository.addPermission(teamData = teamId, userData = binding.userData!!.id)
                    }
                }

                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(binding.root.context, "권한이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                    Log.d("박희찬Permission","권한 변경 성공")
                    Log.d("박희찬Permission", response.body().toString())
                    (binding.root.context as AuthorityManageActivity).finish()
                } else {
//                    BadSnackBar.make(
//                        binding.root,
//                        "권한 변경에 실패 했어요",
//                        "권한을 변경하는데 실패했어요. 잠시 뒤에 다시 시도해주세요",
//                        700
//                    ).show()
                    Log.d("박희찬Permission","권한 변경 실패")
                }

            }
        }
    }
}