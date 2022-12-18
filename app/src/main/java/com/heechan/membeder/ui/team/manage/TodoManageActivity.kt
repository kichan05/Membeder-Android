package com.heechan.membeder.ui.team.manage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTodoManageBinding

class TodoManageActivity : BaseActivity<ActivityTodoManageBinding>(R.layout.activity_todo_manage){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.hdTeamBuilding.setNavigationClickListener {
            finish()
        }
    }
}