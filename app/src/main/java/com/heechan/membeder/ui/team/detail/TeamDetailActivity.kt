package com.heechan.membeder.ui.team.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTeamDetailBinding
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.util.ExtraKey

class TeamDetailActivity : BaseActivity<ActivityTeamDetailBinding>(R.layout.activity_team_detail) {
    val viewModel: TeamDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.teamData.value = intent.getParcelableExtra<Team>(ExtraKey.TEAM_DATA.key)!!

        binding.hdTeamDetail.title = viewModel.teamData.value!!.name

        binding.hdTeamDetail.setNavigationClickListener {
            finish()
        }

        viewModel.teamData.value = intent.getParcelableExtra(ExtraKey.TEAM_DATA.key)
    }
}