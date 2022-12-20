package com.heechan.membeder.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.databinding.FragmentSignUp8Binding
import com.heechan.membeder.databinding.FragmentSignUp9Binding

class SignUp9Fragment : SignUpFragment<FragmentSignUp9Binding>(R.layout.fragment_sign_up_9) {
    val viewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.currentPage = currentPage

        binding.hdSingUp9.setNavigationClickListener {
            gotoPrev()
        }

        binding.imgSignup9PreviewProfileImage.setOnClickListener((activity as SignUpActivity).gotoSelectProfileImage)

        binding.next.setOnClickListener {
            hideKeyboard()
            viewModel.signUp()
        }
    }

    override val currentPage: Int = 9
}