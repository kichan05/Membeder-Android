package com.heechan.membeder.view.snack

import android.view.View
import androidx.databinding.DataBindingUtil
import com.heechan.membeder.R
import com.heechan.membeder.databinding.SnackbarBadBinding

class BadSnackBar(
    private val view : View,
    private val title : String,
    private val message : String,
    private val duration: Int
) : CustomSnackBar(view, duration) {
    companion object {
        fun make(
            view: View,
            title: String,
            message: String,
            duration: Int = 3000,
        ) = BadSnackBar(view, title, message, duration)
    }

    override val binding: SnackbarBadBinding =
        DataBindingUtil.inflate(inflater, R.layout.snackbar_bad, null, false)

    override fun initData() {
        with(binding) {
            txtBadSnackBarTitle.text = title
            txtBadSnackBarMessage.text = message
        }
    }

    override fun setAction(actionBtnTitle: String, clickListener: View.OnClickListener) {
        with(binding.txtBadSnackBarActionBtn){
            visibility = View.VISIBLE
            text = actionBtnTitle

            setOnClickListener() {
                clickListener.onClick(it)
                dismiss()
            }
        }
    }

    init {
        initData()
        initView()
    }
}