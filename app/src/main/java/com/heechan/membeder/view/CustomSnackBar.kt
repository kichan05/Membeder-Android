package com.heechan.membeder.view

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import com.heechan.membeder.R
import com.heechan.membeder.databinding.SnackbarBadBinding
import com.heechan.membeder.databinding.SnackbarGoodBinding

class CustomSnackBar<D : ViewDataBinding>(
    private val parent: View,
    private val title: String,
    private val message: String,
    private val duration: Int,
    private val type : SnackBarType
) {

    companion object {
        fun <D : ViewDataBinding> make(
            view: View,
            title: String,
            message: String,
            duration: Int = 3000,
            type: SnackBarType
        ) = CustomSnackBar<D>(view, title, message, duration, type)
    }

    private val context = parent.context
    private val snackBar = Snackbar.make(parent, "", duration)
    private val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout


    private val snackBarLayoutRes = if (type == SnackBarType.GOOD) {
        R.layout.snackbar_good
    } else {
        R.layout.snackbar_bad
    }

    private val inflater = LayoutInflater.from(context)
    private val binding: D = DataBindingUtil.inflate(inflater, snackBarLayoutRes, null, false)


    init {
        initView()
        initData()
    }

    private fun initView() {
        with(snackBarLayout) {
            removeAllViews()
            setPadding(16, 16, 16, 16)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(binding.root, 0)
        }
    }

    private fun initData() {
        if (binding is SnackbarGoodBinding) {
            with(binding) {
                txtGoodSnackBarTitle.text = title
                txtGoodSnackBarMessage.text = message
            }
        } else if (binding is SnackbarBadBinding) {
            with(binding) {
                txtBadSnackBarTitle.text = title
                txtBadSnackBarMessage.text = message
            }
        }
    }

    fun show() {
        snackBar.show()
    }

    fun dismiss() {
        snackBar.dismiss()
    }

    fun setAction(actionBtnTitle: String, eventListener: View.OnClickListener): Unit {
        if (binding is SnackbarGoodBinding) {
            binding.txtGoodSnackBarActionBtn.apply {
                visibility = View.VISIBLE
                text = actionBtnTitle
                setOnClickListener {
                    snackBar.dismiss()
                    eventListener.onClick(it)
                }
            }
        } else if (binding is SnackbarBadBinding) {
            binding.txtGoodSnackBarActionBtn.apply {
                visibility = View.VISIBLE
                text = actionBtnTitle
                setOnClickListener {
                    snackBar.dismiss()
                    eventListener.onClick(it)
                }
            }
        }
    }

}