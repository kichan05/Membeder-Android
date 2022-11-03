package com.heechan.membeder.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentSignUp3Binding

class SignUp3Fragment : SignUpFragment<FragmentSignUp3Binding>(R.layout.fragment_sign_up_3) {
    val viewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.currentPage = currentPage

        binding.hdSingUp3.setNavigationClickListener {
            requireActivity().finish()
        }

        binding.next.setOnClickListener {
            gotoNext()
        }
    }

    override val currentPage: Int = 3
}