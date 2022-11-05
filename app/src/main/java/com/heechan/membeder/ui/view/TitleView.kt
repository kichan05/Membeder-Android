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
import com.heechan.membeder.databinding.ViewSignUpProgressBinding
import com.heechan.membeder.databinding.ViewTitleBinding


class TitleView : LinearLayout {
    lateinit var view : ViewTitleBinding

    constructor(context : Context) : super(context) {
        initView(context)
    }

    constructor(context : Context, attrs : AttributeSet) : super(context, attrs) {
        initView(context)
        getAttrs(attrs)
    }

    constructor(context : Context, attrs : AttributeSet, defStyle : Int) : super(context, attrs, defStyle) {
        initView(context)
        getAttrs(attrs, defStyle)
    }

    private fun initView(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = ViewTitleBinding.inflate(inflater)

        val lp = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        view.root.layoutParams = lp

        addView(view.root)
    }

    private fun getAttrs(attrs: AttributeSet): Unit {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SignUpProgress)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle : Int): Unit {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SignUpProgress, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val currentStep = typedArray.getInt(R.styleable.SignUpProgress_signUpProgressIndex, -1)

        val title = typedArray.getString(R.styleable.TitleView_titleView_title)
        val subTitle = typedArray.getString(R.styleable.TitleView_titleView_subTitle)
        val message = typedArray.getString(R.styleable.TitleView_titleView_message)


        if(title == null)
            throw Exception("TitleView title is empty")

        if(subTitle == null)
            throw Exception("TitleView sub title is empty")

        with(view) {
            txtTitleViewTitle.text = title
            txtTitleViewSubTitle.text = subTitle

            if(message == null)
                txtTitleViewMessage.visibility = View.GONE
            else
                txtTitleViewMessage.text = message
        }

    }

}