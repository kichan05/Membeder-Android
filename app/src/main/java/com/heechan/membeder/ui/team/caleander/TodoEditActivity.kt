package com.heechan.membeder.ui.team.caleander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.ActivityTodoEditBinding
import com.heechan.membeder.databinding.FragmentHomeTeamBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.main.MainViewModel

class TodoEditActivity : BaseActivity<ActivityTodoEditBinding>(R.layout.activity_todo_edit){
    val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.singleton = SingletonObject
    }
}