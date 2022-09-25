package com.heechan.membeder.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentEmailAndPasswordBinding

class EmailAndPasswordFragment : BaseFragment<FragmentEmailAndPasswordBinding>(R.layout.fragment_email_and_password){
    val viewModel : RegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_emailAndPasswordFragment_to_professionFragment)
        }
    }
}