package com.heechan.membeder.ui.team.manage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMemberBanBinding
import com.heechan.membeder.databinding.ActivityTeamProfileEditBinding
import com.heechan.membeder.model.data.schedule.TodoEditReq
import com.heechan.membeder.model.data.team.Applicant
import com.heechan.membeder.model.data.team.EditTeamReq
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.team.caleander.TodoFinalEditActivity
import com.heechan.membeder.ui.team.detail.TeamDetailViewModel
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import com.heechan.membeder.util.ExtraKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamProfileEditActivity :
    BaseActivity<ActivityTeamProfileEditBinding>(R.layout.activity_team_profile_edit) {
    val viewModel: TeamProfileEditViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val teamId = SingletonObject.selectTeam.value!!.id
        val repository = TeamRepositoryImpl()
        viewModel.getTeamData(teamId)
        super.onCreate(savedInstanceState)
        viewModel.teamData.observe(this) {
            binding.edtTeamEditName.setText(viewModel.teamData.value!!.name)
            binding.edtScheduleNameDecription.setText(viewModel.teamData.value!!.description)
            binding.txtTeameditDveloper.setText(viewModel.teamData.value!!.applicant.developer.toString())
            binding.txtTeameditDesigner.setText(viewModel.teamData.value!!.applicant.designer.toString())
            binding.txtTeameditDirector.setText(viewModel.teamData.value!!.applicant.director.toString())
        }
        var a = 0
        binding.hdEditTeamProfile.setNavigationClickListener {
            finish()
        }
        binding.btnTodofinalTodoEdit.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    val applicant = Applicant(
                        developer = binding.txtTeameditDveloper.text.toString().toInt(),
                        designer = binding.txtTeameditDesigner.text.toString().toInt(),
                        director = binding.txtTeameditDirector.text.toString().toInt()
                    )
                    val request = EditTeamReq(
                        name = binding.edtTeamEditName.text.toString(),
                        description = binding.edtScheduleNameDecription.text.toString(),
                        private = viewModel.teamData.value!!.private,
                        image = viewModel.teamData.value!!.image,
                        applicant = applicant
                    )
                    repository.EditTeam(
                        id = teamId, teamData = request
                    )
                }
                if(response.isSuccessful)
                    GoodSnackBar.make(
                        binding.root,
                        "수정 성공!",
                        "팀 프로필 수정에\n 성공하였습니다.",
                        700,
                    ).show()
            }


        }

    }
}