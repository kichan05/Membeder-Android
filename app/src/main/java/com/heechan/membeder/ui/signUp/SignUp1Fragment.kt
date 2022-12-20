package com.heechan.membeder.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.databinding.FragmentSignUp1Binding

class SignUp1Fragment : SignUpFragment<FragmentSignUp1Binding>(R.layout.fragment_sign_up_1) {
    val viewModel: SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            // Todo: MVVM에 맞지 않음, 수정 필요
            binding.errSignUp1.apply {
                if (it != null) {
                    errorMessasge = it
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }
        }

        binding.btnSignUp1ProfileImage.setOnClickListener((activity as SignUpActivity).gotoSelectProfileImage)

        binding.hdSingUp1.setNavigationClickListener {
            requireActivity().finish()
        }

        binding.next.setOnClickListener {
            hideKeyboard()
            if (viewModel.inputCheckEmailPassword()) {
                viewModel.errorMessage.value = null
                gotoNext()
            }
        }
    }

    override val currentPage: Int = 1
}