package com.heechan.membeder.ui.common

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("bindVisibility")
fun bindVisibility(view : View, isVisible : Boolean) {
    view.visibility = if(isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("bindRadioButtonValue")
fun bindRadioButtonValue(rb : RadioButton, value : Int) {
    rb.isChecked = rb.id == value
}

@BindingAdapter("bindDateFormat")
fun bindDateFormat(txtView : TextView, date : LocalDate) {
}