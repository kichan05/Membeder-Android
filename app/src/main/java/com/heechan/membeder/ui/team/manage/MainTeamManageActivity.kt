package com.heechan.membeder.ui.team.manage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.databinding.ActivityMainTeamManageBinding
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.ui.team.joinReq.JoinRequestListActivity

class MainTeamManageActivity : BaseActivity<ActivityMainTeamManageBinding>(R.layout.activity_main_team_manage) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.txt_membermanage_memberrecruitment).setOnClickListener {
            val intent = Intent(this, JoinRequestListActivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.txt_membermanage_memberban).setOnClickListener {
            val intent = Intent(this, MemberBanActivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.txt_membermanage_authority).setOnClickListener{
            val intent = Intent(this, AuthorityManageActivity::class.java)
            startActivity(intent)
        }
        binding.hdTeamBuilding.setNavigationClickListener {
            finish()
        }
    }
}