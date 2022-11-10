package com.heechan.membeder.ui.main

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.databinding.RowTeamBuildingListItemBinding
import com.heechan.membeder.databinding.RowTeamSelectListItemBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.common.bindDateFormat
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import kotlinx.coroutines.*

class TeamBuildingListViewHolder(private val view: RowTeamBuildingListItemBinding) :
    RecyclerView.ViewHolder(view.root) {
    private val repository = TeamRepositoryImpl()

    lateinit var teamData: Team

    fun onBind(teamData: Team) {
        this.teamData = teamData
        view.teamData = teamData
    }

    init {
        view.root.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch(
                CoroutineExceptionHandler { _, e ->
                    Log.d("[TeamMemberAdd]", e.toString())
                }
            ) {
                val response = repository.addMember(
                    team_id = teamData.id,
                    user_id = SingletonObject.userData!!.id
                )

                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main) {
                        CoroutineScope(Dispatchers.Main).launch {
                            GoodSnackBar.make(view.root, "팀에 가입되었습니다.", "팀원들과 함께 일을 해봐요").show()
                        }
                    }
                }
            }
        }
    }
}