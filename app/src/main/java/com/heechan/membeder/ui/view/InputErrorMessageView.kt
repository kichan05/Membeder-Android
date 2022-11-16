package com.heechan.membeder.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.heechan.membeder.R
import com.heechan.membeder.databinding.ViewInputErroeMessageBinding


class InputErrorMessageView : LinearLayout {
    lateinit var view: ViewInputErroeMessageBinding

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView(context)
        getAttrs(attrs, defStyle)

    }

    private fun initView(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = ViewInputErroeMessageBinding.inflate(inflater)

        val lp = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        view.root.layoutParams = lp

        addView(view.root)
    }

    fun getAttrs(attrs: AttributeSet): Unit {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputErrorMessageView)
        setTypeArray(typedArray)
    }

    fun getAttrs(attrs: AttributeSet, defStyle: Int): Unit {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.InputErrorMessageView, defStyle, 0)
        setTypeArray(typedArray)
    }

    var errorMessasge: String
        get() = view.txtInputErrorMessage.text.toString()
        set(v : String) {
            view.txtInputErrorMessage.text = v
        }

    private fun setTypeArray(typedArray: TypedArray) {
        val message =
            typedArray.getString(R.styleable.InputErrorMessageView_inputErrorMessageMessage)
        Log.d("InputErrorMessageView", "message : $message")
        view.txtInputErrorMessage.text = message
    }

}