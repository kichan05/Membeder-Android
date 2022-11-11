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

        viewModel.erroeMessage.observe(viewLifecycleOwner) {

            binding.errSignUp1.apply {
                if (it != null) {
                    errorMessasge = it
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }
        }

        binding.hdSingUp1.setNavigationClickListener {
            requireActivity().finish()
        }
        binding.next.setOnClickListener {
            if (viewModel.inputCheckEmailPassword()) {
                viewModel.erroeMessage.value = null
                gotoNext()
            }
        }
    }

    override val currentPage: Int = 1
}