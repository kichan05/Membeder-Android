package com.heechan.membeder.ui.team.caleander

import android.os.Bundle
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTodoEditBinding
import com.heechan.membeder.ui.SingletonObject

class TodoEditActivity : BaseActivity<ActivityTodoEditBinding>(R.layout.activity_todo_edit) {
    val viewModel: TodoEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.singleton = SingletonObject
        binding.hdTodoEditTitle.setNavigationClickListener {
            finish()
        }


    }

}