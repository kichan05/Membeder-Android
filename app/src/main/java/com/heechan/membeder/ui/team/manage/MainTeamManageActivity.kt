package com.heechan.membeder.ui.team.manage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.heechan.membeder.R

class MainTeamManageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_team_manage)
        findViewById<TextView>(R.id.txt_membermanage_memberrecruitment).setOnClickListener {
            val intent = Intent(this, MemberRecruitmentActivity::class.java)
            startActivity(intent)
        }
    }
}