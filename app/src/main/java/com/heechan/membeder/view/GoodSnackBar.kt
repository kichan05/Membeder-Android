package com.heechan.membeder.view

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.heechan.membeder.R
import com.heechan.membeder.databinding.SnackbarGoodBinding

class GoodSnackBar(
    private val parent: View,
    private val title: String,
    private val message: String,
) {

    companion object {
        fun make(view: View, title: String, message: String) = GoodSnackBar(view, title, message)
    }

    private val context = parent.context
    private val snackBar = Snackbar.make(parent, "", 5000)
    private val snackbarLayout = snackBar.view as Snackbar.SnackbarLayout

    private val inflater = LayoutInflater.from(context)
    private val binding: SnackbarGoodBinding =
        DataBindingUtil.inflate(inflater, R.layout.snackbar_good, null, false)

    init {
        initView()
        initData()
    }

    private fun initView() {
        with(snackbarLayout) {
            removeAllViews()
            setPadding(0, 0, 0, 0)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(binding.root, 0)
        }
    }

    private fun initData() {
        with(binding) {
            txtGoodSnackBarTitle.text = title
            txtGoodSnackBarMessage.text = message
//            txtGoodSnackBarActionBtn.text = actionBtnTitle
        }
    }

    fun show() {
        snackBar.show()
    }

    fun setAction(actionBtnTitle: String, eventListener: View.OnClickListener): Unit {
        binding.txtGoodSnackBarActionBtn.apply {
            text = actionBtnTitle
            setOnClickListener(eventListener)
        }

    }

}