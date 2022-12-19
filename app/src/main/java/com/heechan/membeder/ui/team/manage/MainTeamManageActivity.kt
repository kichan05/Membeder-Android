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
import com.heechan.membeder.ui.team.caleander.TodoDeleteActivity
import com.heechan.membeder.ui.team.caleander.TodoEditActivity
import com.heechan.membeder.ui.team.joinReq.JoinRequestListActivity

class MainTeamManageActivity : BaseActivity<ActivityMainTeamManageBinding>(R.layout.activity_main_team_manage) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}