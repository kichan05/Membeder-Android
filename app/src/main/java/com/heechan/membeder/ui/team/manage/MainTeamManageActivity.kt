package com.heechan.membeder.ui.team.manage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.databinding.ActivityMainTeamManageBinding
import com.heechan.membeder.model.data.team.Applicant
import com.heechan.membeder.model.data.team.EditTeamReq
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.ui.team.caleander.TodoDeleteActivity
import com.heechan.membeder.ui.team.caleander.TodoEditActivity
import com.heechan.membeder.ui.team.joinReq.JoinRequestListActivity
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import com.heechan.membeder.util.ExtraKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainTeamManageActivity : BaseActivity<ActivityMainTeamManageBinding>(R.layout.activity_main_team_manage) {
    val viewModel: TeamProfileEditViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val teamId = SingletonObject.selectTeam.value!!.id
        val repository = TeamRepositoryImpl()
        viewModel.getTeamData(teamId)
        viewModel.teamData.observe(this) {
            binding.swItemOnOff.isChecked = viewModel.teamData.value!!.private
        }
        binding.swItemOnOff.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    val applicant = Applicant(
                        developer = viewModel.teamData.value!!.applicant.developer,
                        designer = viewModel.teamData.value!!.applicant.developer,
                        director = viewModel.teamData.value!!.applicant.developer
                    )
                    val request = EditTeamReq(
                        name = viewModel.teamData.value!!.name,
                        description = viewModel.teamData.value!!.description,
                        private = binding.swItemOnOff.isChecked,
                        image = viewModel.teamData.value!!.image,
                        applicant = applicant
                    )
                    repository.EditTeam(
                        id = teamId, teamData = request
                    )
                }
            }
        }
        binding.txtMembermanageMemberrecruitment.setOnClickListener {
            val intent = Intent(this, JoinRequestListActivity::class.java)
            startActivity(intent)
        }
        binding.txtMembermanageMemberban.setOnClickListener {
            val intent = Intent(this, MemberBanActivity::class.java)
            startActivity(intent)
        }
        binding.txtMembermanageAuthority.setOnClickListener{
            val intent = Intent(this, AuthorityManageActivity::class.java)
            startActivity(intent)
        }
        binding.hdTeamBuilding.setNavigationClickListener {
            finish()
        }
        binding.txtCaleandermanageEdit.setOnClickListener {
            val intent = Intent(this, TodoEditActivity::class.java)
            startActivity(intent)
        }
        binding.txtCaleandermanageDelete.setOnClickListener {
            val intent = Intent(this, TodoDeleteActivity::class.java)
            startActivity(intent)
        }
        binding.txtOtherProfileEdit.setOnClickListener {
            val intent = Intent(this, TeamProfileEditActivity::class.java)
            startActivity(intent)
        }
        binding.btnOtherNoticeSetting.setOnClickListener {
            val intent = Intent(this, AddNoticeActivity::class.java)
            startActivity(intent)
        }
    }
}