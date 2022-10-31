package com.heechan.membeder.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.heechan.membeder.R


class TagView : LinearLayout {
    lateinit var txtTagTitle : TextView

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
        val view = LayoutInflater.from(context).inflate(R.layout.view_tag, this, false)
        addView(view)

        txtTagTitle = view.findViewById(R.id.txt_tagView_title)
    }

    fun getAttrs(attrs: AttributeSet): Unit {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Tag)
        setTypeArray(typedArray)
    }

    fun getAttrs(attrs: AttributeSet, defStyle : Int): Unit {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Tag, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val tagTitle = typedArray.getString(R.styleable.Tag_tagTitle)
        txtTagTitle.text = tagTitle

        typedArray.recycle()
    }

}