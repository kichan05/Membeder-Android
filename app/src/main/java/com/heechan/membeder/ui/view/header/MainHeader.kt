package com.heechan.membeder.ui.view.header

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.heechan.membeder.R
import com.heechan.membeder.databinding.ViewHeaderMainBinding

class MainHeader : LinearLayout {
    lateinit var view: ViewHeaderMainBinding

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

    fun setMenu1IconClickListener(listener : (icon : View) -> Unit){
        view.imgSplashMenu1.setOnClickListener(listener)
    }

    fun setMenu2IconClickListener(listener : (icon : View) -> Unit){
        view.imgSplashMenu2.setOnClickListener(listener)
    }


    private fun initView(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = ViewHeaderMainBinding.inflate(inflater)

        val lp = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        view.root.layoutParams = lp


        addView(view.root)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderMain)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderMain, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val menuIcon1 = typedArray.getResourceId(
            R.styleable.HeaderMain_mainMenu1Icon,
            R.drawable.ic_bnv_contest_fill
        )
        val menuIcon2 = typedArray.getResourceId(
            R.styleable.HeaderMain_mainMenu2Icon,
            R.drawable.ic_bnv_contest_fill
        )

        if(menuIcon1 == R.drawable.ic_bnv_contest_fill){
            view.imgSplashMenu1.visibility = View.GONE
        }
        if(menuIcon2 == R.drawable.ic_bnv_contest_fill){
            view.imgSplashMenu2.visibility = View.GONE
        }


        view.imgSplashMenu1.setImageResource(menuIcon1)
        view.imgSplashMenu2.setImageResource(menuIcon2)
    }
}