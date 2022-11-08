package com.heechan.membeder.ui.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class ScheduleAddViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val account = MutableLiveData<String>()
    val deadLine = MutableLiveData<LocalDate>()
}