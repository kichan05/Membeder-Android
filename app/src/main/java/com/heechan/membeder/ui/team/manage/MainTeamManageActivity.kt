package com.heechan.membeder.ui.team.manage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.heechan.membeder.R
import com.heechan.membeder.ui.team.joinReq.JoinRequestListActivity

class MainTeamManageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_team_manage)
        findViewById<TextView>(R.id.txt_membermanage_memberrecruitment).setOnClickListener {
            val intent = Intent(this, JoinRequestListActivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.txt_membermanage_authority).setOnClickListener{
            val intent = Intent(this, AuthorityManageActivity::class.java)
            startActivity(intent)
        }
    }
}