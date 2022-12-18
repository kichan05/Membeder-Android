package com.heechan.membeder.ui.team.manage

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.AuthoritymanageItemBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.remote.TeamPermissionRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.view.snack.BadSnackBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorityManageListViewHolder(private val binding : AuthoritymanageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val repository = TeamPermissionRepositoryImpl()
    private val teamId = SingletonObject.selectTeam.value!!.id
    private val userId = SingletonObject.userData.value!!.id

    fun onBind(userData : User,isOwner : Boolean){
        binding.userData = userData
        binding.isOwner = isOwner
    }
    init {
        binding.swItemOnOff.setOnCheckedChangeListener { compoundButton, b ->
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    if(b)
                    {
                        repository.deletePermission(teamData = teamId, userData = binding.userData!!.id)
                    }
                    else
                    {
                        repository.addPermission(teamData = teamId, userData = binding.userData!!.id)
                    }
                }
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(binding.root.context, "권한이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                    (binding.root.context as AuthorityManageActivity).finish()
                } else {
                    BadSnackBar.make(
                        binding.root,
                        "권한 변경에 실패 했어요",
                        "권한을 변경하는데 실패했어요. 잠시 뒤에 다시 시도해주세요",
                        700
                    ).show()
                }
            }
        }
    }
}