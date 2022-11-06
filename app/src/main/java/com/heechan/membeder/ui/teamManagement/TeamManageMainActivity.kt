package com.heechan.membeder.ui.teamManagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.heechan.membeder.R

class TeamManageMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_mange_main)
        findViewById<Button>(R.id.btn_manage_ban).setOnClickListener {
            var intent = Intent(this,TeamManageBanActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_manage_invite).setOnClickListener {

        }
    }
}