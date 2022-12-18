package com.heechan.membeder.ui.team.manage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            binding.rvMemberbanmanage.adapter = AuthorityManageListAdapter(it!!.member.filter { it.id != SingletonObject.userData.value!!.id }, viewModel.teamData.value!!.isOwner)
            binding.rvMemberbanmanage.adapter!!.notifyDataSetChanged()
        }
        binding.hdTeamBuilding.setNavigationClickListener {
            finish()
        }

    }
}