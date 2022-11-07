package com.heechan.membeder.ui.signUp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.*
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.util.State

class SignUp9Fragment : SignUpFragment<FragmentSignUp9Binding>(R.layout.fragment_sign_up_9) {
    val viewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.currentPage = currentPage

        binding.next.setOnClickListener((activity as SignUpActivity).gotoMain)
    }

    override val currentPage: Int = 9
}