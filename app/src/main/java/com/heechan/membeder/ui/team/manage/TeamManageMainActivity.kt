package com.heechan.membeder.ui.team.manage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.heechan.membeder.R
import com.heechan.membeder.ui.team.select.TeamSelectActivity

class TeamManageMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_mange_main)
        findViewById<Button>(R.id.btn_manage_ban).setOnClickListener {
            var intent = Intent(this, TeamSelectActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_manage_invite).setOnClickListener {

        }
    }
}