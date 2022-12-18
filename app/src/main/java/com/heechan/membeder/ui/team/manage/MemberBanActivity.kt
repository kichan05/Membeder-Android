package com.heechan.membeder.ui.team.manage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainTeamManageBinding
import com.heechan.membeder.databinding.ActivityMemberBanBinding

class MemberBanActivity : BaseActivity<ActivityMemberBanBinding>(R.layout.activity_member_ban) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.hdTeamBuilding.setNavigationClickListener {
            finish()
        }
    }
}