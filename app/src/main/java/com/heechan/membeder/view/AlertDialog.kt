package com.heechan.membeder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.heechan.membeder.databinding.DialogAlertBinding

class AlertDialog(val title: String, val message: String, val okCallBack: () -> Unit) :
    DialogFragment() {
    private lateinit var binding: DialogAlertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAlertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            txtAlertDialogTitle.text = title
            txtAlertDialogMessage.text = message

            btnAlertDialogOk.setOnClickListener{
                dismiss()
                okCallBack()
            }
            btnAlertDialogCancle.setOnClickListener {
                dismiss()
            }
        }
    }
}