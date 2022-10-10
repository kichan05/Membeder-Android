package com.heechan.membeder.view.snack

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.heechan.membeder.R
import com.heechan.membeder.databinding.SnackbarGoodBinding

class GoodSnackBar(
    private val view : View,
    private val title : String,
    private val message : String,
    duration: Int
) : CustomSnackBar(view, duration) {
    companion object {
        fun make(
            view: View,
            title: String,
            message: String,
            duration: Int = 3000,
        ) = GoodSnackBar(view, title, message, duration)
    }

    override val binding: SnackbarGoodBinding =
        DataBindingUtil.inflate(inflater, R.layout.snackbar_good, null, false)

    override fun initData() {
        with(binding) {
            txtGoodSnackBarTitle.text = title
            txtGoodSnackBarMessage.text = message
        }
    }

    override fun setAction(actionBtnTitle: String, clickListener: View.OnClickListener) {
        with(binding.txtGoodSnackBarActionBtn){
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