package com.heechan.membeder.ui.team.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTeamDetailBinding
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.util.ExtraKey
import com.heechan.membeder.util.State
import com.heechan.membeder.util.State.*

class TeamDetailActivity : BaseActivity<ActivityTeamDetailBinding>(R.layout.activity_team_detail) {
    val viewModel: TeamDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel.teamData.value = intent.getParcelableExtra(ExtraKey.TEAM_DATA.key)

        val teamId = intent.getStringExtra(ExtraKey.TEAM_DATA.key)!!
        viewModel.getTeamData(teamId)

        viewModel.teamData.observe(this) {
            if (it == null) return@observe

            binding.txtTeamDetailTeamDescriptionTitle.visibility = View.VISIBLE
            binding.hdTeamDetail.title = it.name
        }

        binding.hdTeamDetail.setNavigationClickListener {
            finish()
        }

        binding.btnTeamDetailJoin.setOnClickListener {
            viewModel.joinRequestTeam(teamId)
        }

        viewModel.joinRequestState.observe(this) {
            when (it) {
                SUCCESS -> {
                    Toast.makeText(this, "가입 신청을 완료했어요", Toast.LENGTH_SHORT).show()
                    finish()
                }
                LOADING -> {}
                FAIL -> {
                    BadSnackBar.make(
                        binding.root,
                        "팀 신청에 실패했어요",
                        "${viewModel.teamData.value!!.name}팀에 신청을 실패했어요, 다시 시도해주세요",
                        700
                    ).show()
                }
            }
        }
    }
}