package com.heechan.membeder.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    val viewModel : RegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        binding.next.setOnClickListener {
            viewModel.signUp()
        }
    }
}