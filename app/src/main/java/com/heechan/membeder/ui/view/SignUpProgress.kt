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


class SignUpProgress : LinearLayout {
    lateinit var view : ViewSignUpProgressBinding

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
        view = ViewSignUpProgressBinding.inflate(inflater)

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

        val stepList : List<View> = view.run {
            listOf(
                viewSignUpProgress1,
                viewSignUpProgress2,
                viewSignUpProgress3,
                viewSignUpProgress4,
                viewSignUpProgress5,
                viewSignUpProgress6,
            )
        }

        stepList.forEachIndexed { index, view ->
            if(currentStep <= index){
                Log.d("signUpProgress", "현재 : ${currentStep} 꺼짐 ${index}")
                view.setBackgroundResource(R.drawable.sh_sign_up_progress_default)
            }
            else {
                Log.d("signUpProgress", "현재 : ${currentStep} 켜짐 ${index}")
                view.setBackgroundResource(R.drawable.sh_sign_up_progress_active)
            }
        }
    }

}