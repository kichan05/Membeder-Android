package com.heechan.membeder.ui.team.caleander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.ActivityTodoEditBinding
import com.heechan.membeder.databinding.FragmentHomeTeamBinding
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.main.MainViewModel

class TodoEditActivity : BaseFragment<ActivityTodoEditBinding>(R.layout.activity_todo_edit){
    val viewModel : MainViewModel by activityViewModels()

    override fun onCreate(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel
        binding.singleton = SingletonObject
        return binding.root
    }
}