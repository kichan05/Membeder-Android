package com.heechan.membeder.view.snack

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.heechan.membeder.R
import com.heechan.membeder.databinding.SnackbarBadBinding
import com.heechan.membeder.databinding.SnackbarGoodBinding
import com.heechan.membeder.view.SnackBarType

abstract class CustomSnackBar(
    private val parent: View,
    private val title: String,
    private val message: String,
    private val duration: Int,
) {


    private val context = parent.context
    private val snackBar = Snackbar.make(parent, "", duration)
    private val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout

    protected val inflater = LayoutInflater.from(context)
    protected abstract val binding : ViewBinding

    protected abstract fun initData()

    protected fun initView() {
        with(snackBarLayout) {
            removeAllViews()
            setPadding(4, 4, 4, 4)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(binding.root, 0)
        }
    }


    fun show() {
        snackBar.show()
    }

    fun dismiss() {
        snackBar.dismiss()
    }

    open fun setAction(actionBtnTitle : String, clickListener: View.OnClickListener) {}
}