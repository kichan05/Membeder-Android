package com.heechan.membeder.ui.common

import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("bindRadioButtonValue")
fun bindRadioButtonValue(rb : RadioButton, value : Int) {
    rb.isChecked = rb.id == value
}

@BindingAdapter("bindDateFormat")
fun bindDateFormat(txtView : TextView, date : LocalDate) {
}