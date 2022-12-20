package com.heechan.membeder.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.databinding.*

class SignUp10Fragment : SignUpFragment<FragmentSignUp10Binding>(R.layout.fragment_sign_up_10) {
    val viewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.currentPage = currentPage

        binding.next.setOnClickListener((activity as SignUpActivity).gotoMain)
    }

    override val currentPage: Int = 10
}