package com.heechan.membeder.ui

import android.widget.RadioButton
import androidx.databinding.BindingAdapter

@BindingAdapter("bindRadioButtonValue")
fun bindRadioButtonValue(rb : RadioButton, value : Int) {
    rb.isChecked = rb.id == value
}