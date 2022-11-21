package com.heechan.membeder.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.databinding.FragmentSignUp2Binding

class SignUp2Fragment : SignUpFragment<FragmentSignUp2Binding>(R.layout.fragment_sign_up_2){
    val viewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.currentPage = currentPage

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.errSignUp2.apply {
                if (it != null) {
                    errorMessasge = it
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }
        }

        binding.hdSingUp2.setNavigationClickListener {
            gotoPrev()
        }

        binding.next.setOnClickListener {
            hideKeyboard()
            if(viewModel.inputCheckNameNickName()){
                viewModel.errorMessage.value = null
                gotoNext()
            }
        }
    }

    override val currentPage: Int = 2
}