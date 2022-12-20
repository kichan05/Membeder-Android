package com.heechan.membeder.ui.team.manage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityAuthorityManageBinding
import com.heechan.membeder.databinding.ActivityMainTeamManageBinding
import com.heechan.membeder.ui.SingletonObject

class AuthorityManageActivity : BaseActivity<ActivityAuthorityManageBinding>(R.layout.activity_authority_manage) {
    val viewModel : AuthorityManageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        val teamId = SingletonObject.selectTeam.value!!.id
        viewModel.getTeamData(teamId)

        viewModel.teamData.observe(this){
            val userList = it!!.member.filter { it.id != SingletonObject.userData.value!!.id }  // 모든 유저들
            val permissionList = it!!.permission.filter { it.id != SingletonObject.userData.value!!.id } // 권한이 있는 사람들
            //userList 원소 중 permissionList에 없는 원소를 map 함수를 사용해 false 로 바꾸고 나머지는 true 로 바꿔서 저장.
            val switchList = userList.map { user -> permissionList.find { it.id == user.id } == null }.toMutableList()
            Log.d("퍼미션 userList", userList.toString())
            Log.d("퍼미션 permissionList", permissionList.toString())
            Log.d("퍼미션 switchList", switchList.toString())
            binding.rvMemberbanmanage.adapter = AuthorityManageListAdapter(it!!.member.filter { it.id != SingletonObject.userData.value!!.id }, viewModel.teamData.value!!.isOwner, switchList)
            binding.rvMemberbanmanage.adapter!!.notifyDataSetChanged()
        }
        binding.hdTeamBuilding.setNavigationClickListener {
            finish()
        }

    }
}