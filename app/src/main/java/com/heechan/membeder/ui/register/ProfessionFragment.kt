package com.heechan.membeder.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentProfessionBinding

class ProfessionFragment : BaseFragment<FragmentProfessionBinding>(R.layout.fragment_profession) {
    val viewModel : RegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_professionFragment_to_profileFragment)
        }
    }
}