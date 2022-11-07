package com.heechan.membeder.ui.teamManagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heechan.membeder.R
import com.heechan.membeder.model.data.SingletonObject

class SelectTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_manage_selcect_team)

        val teamlist = SingletonObject.userData!!.teamList
        findViewById<RecyclerView>(R.id.list_main).apply {
            adapter = TeamManageAdapter(teamlist)
            layoutManager = LinearLayoutManager(this@SelectTeamActivity)
        }
    }
}