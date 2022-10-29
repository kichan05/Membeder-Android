package com.heechan.membeder.ui.teamMake

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTeamMakeBinding
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.util.State

class TeamMakeActivity : BaseActivity<ActivityTeamMakeBinding>(R.layout.activity_team_make) {
    val viewModel : TeamMakeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this){
            Log.d("teamCreateState", it.toString())
            when(it){
                State.SUCCESS -> {
                    Toast.makeText(this, "팀 생성에 성공했습니다. ${viewModel.resultData.value!!.name}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                State.LOADING -> {
                }
                State.FAIL -> {
                    Toast.makeText(this, "팀 생성 실패함 ㅅㄱ ㅋㅋ", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.tmvm = viewModel

        binding.teammakeTestBtn.setOnClickListener {
            viewModel.makeTeam()
        }
    }
}