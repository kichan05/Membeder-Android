package com.heechan.membeder.ui.team.manage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMemberBanBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.team.joinReq.JoinReqListAdapter
import com.heechan.membeder.ui.team.joinReq.JoinReqViewModel

class MemberBanActivity : BaseActivity<ActivityMemberBanBinding>(R.layout.activity_member_ban) {
    val viewModel : MemberBanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        val teamId = SingletonObject.selectTeam.value!!.id
        viewModel.getTeamData(teamId)

        viewModel.teamData.observe(this) {
            Log.d("JoinRequestListActivity", "teamData : ${it.member}")

            binding.listTeamBuildingTeamList.adapter = MemberBanAdater(it!!.member.filter { it.id != SingletonObject.userData.value!!.id }, viewModel.teamData.value!!.isOwner)
            binding.listTeamBuildingTeamList.adapter!!.notifyDataSetChanged()
        }

        binding.hdTeamBuilding.setNavigationClickListener {
            finish()
        }
    }
}