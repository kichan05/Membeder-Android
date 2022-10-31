package com.heechan.membeder.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentSignUp1Binding
import com.heechan.membeder.databinding.FragmentSignUp2Binding

class SignUp2Fragment : SignUpFragment<FragmentSignUp2Binding>(R.layout.fragment_sign_up_2){
    val viewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_emailAndPasswordFragment_to_professionFragment)
        }
    }

    override val currentPage: Int = 2
}