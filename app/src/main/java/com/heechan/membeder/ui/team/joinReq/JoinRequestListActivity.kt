package com.heechan.membeder.ui.team.joinReq

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityJoinRequestListBinding
import com.heechan.membeder.ui.SingletonObject

class JoinRequestListActivity : BaseActivity<ActivityJoinRequestListBinding>(R.layout.activity_join_request_list) {
    val viewModel : JoinReqViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        val teamId = SingletonObject.selectTeam.value!!.id
        viewModel.getTeamData(teamId)

        viewModel.teamData.observe(this) {
            Log.d("JoinRequestListActivity", "teamData : $it")
            binding.listJoinReqList.adapter = JoinReqListAdapter(it!!.joinRequest, viewModel.teamData.value!!.isOwner)
            binding.listJoinReqList.adapter!!.notifyDataSetChanged()
        }
        binding.hdJoinReqList.setNavigationClickListener {
            finish()
        }
    }
}

