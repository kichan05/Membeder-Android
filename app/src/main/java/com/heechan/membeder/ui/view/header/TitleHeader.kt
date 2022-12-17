package com.heechan.membeder.ui.view.header

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.heechan.membeder.R
import com.heechan.membeder.databinding.ViewHeaderNavigationBinding
import com.heechan.membeder.databinding.ViewHeaderTitleBinding

class TitleHeader : LinearLayout {
    lateinit var view: ViewHeaderTitleBinding

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
        view.imgTitleHeaderMenu1.setOnClickListener(listener)
    }

    fun setMenu2IconClickListener(listener : (icon : View) -> Unit){
        view.imgTitleHeaderMenu2.setOnClickListener(listener)
    }

    var title : String
        get() = view.txtTitleHeaderTitle.text.toString()
        set(value) {
            view.txtTitleHeaderTitle.text = value
        }


    private fun initView(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = ViewHeaderTitleBinding.inflate(inflater)

        val lp = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        view.root.layoutParams = lp

        addView(view.root)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderTitle)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderTitle, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        setTitle(typedArray)
        setMenuIcon(typedArray)
    }

    private fun setTitle(typedArray: TypedArray) {
        val title = typedArray.getString(R.styleable.HeaderNavigation_navigationTitle)
        view.txtTitleHeaderTitle.text = title ?: ""
    }

    private fun setMenuIcon(typedArray: TypedArray) {
        val menuIcon1 = typedArray.getResourceId(
            R.styleable.HeaderNavigation_navigationMenu1Icon,
            R.drawable.ic_bnv_contest_fill
        )
        val menuIcon2 = typedArray.getResourceId(
            R.styleable.HeaderNavigation_navigationMenu2Icon,
            R.drawable.ic_bnv_contest_fill
        )

        if (menuIcon1 == R.drawable.ic_bnv_contest_fill) {
            view.imgTitleHeaderMenu1.visibility = GONE
        } else {
            view.imgTitleHeaderMenu1.setImageResource(menuIcon1)
        }

        if (menuIcon2 == R.drawable.ic_bnv_contest_fill) {
            view.imgTitleHeaderMenu2.visibility = GONE
        } else {
            view.imgTitleHeaderMenu2.setImageResource(menuIcon2)
        }
    }
}