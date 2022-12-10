package com.heechan.membeder.ui.team.select

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTeamSelcectTeamBinding
import com.heechan.membeder.ui.SingletonObject

class TeamSelectActivity :
    BaseActivity<ActivityTeamSelcectTeamBinding>(R.layout.activity_team_selcect_team) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val teamList = SingletonObject.userData.value!!.teamList
        binding.listTeamSelect.apply {
            adapter = TeamSelectListAdapter(teamList)
            layoutManager = LinearLayoutManager(this@TeamSelectActivity)
        }
    }
}