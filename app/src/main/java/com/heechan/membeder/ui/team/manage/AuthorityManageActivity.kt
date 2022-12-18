package com.heechan.membeder.ui.team.manage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityAuthorityManageBinding
import com.heechan.membeder.databinding.ActivityMainTeamManageBinding

class   AuthorityManageActivity : BaseActivity<ActivityAuthorityManageBinding>(R.layout.activity_authority_manage) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authority_manage)
        val memberManageTextView = findViewById<TextView>(R.id.tv_authoritymanage_memeber)
        val todoManageTextView = findViewById<TextView>(R.id.tv_authoritymanage_memeber)
        val memberManageButton = findViewById<ImageView>(R.id.btn_authoritymanage_member)
        val todoManageButton = findViewById<ImageView>(R.id.btn_authoritymanage_todo)
        memberManageTextView.setOnClickListener {
            startActivity(Intent(this, MemberManageActivity::class.java))
        }
        memberManageButton.setOnClickListener {
            startActivity(Intent(this, MemberManageActivity::class.java))
        }
        todoManageTextView.setOnClickListener {
            startActivity(Intent(this, TodoManageActivity::class.java))
        }
        todoManageButton.setOnClickListener {
            startActivity(Intent(this, TodoManageActivity::class.java))
        }
        binding.hdTeamBuilding.setNavigationClickListener {
            finish()
        }

    }
}