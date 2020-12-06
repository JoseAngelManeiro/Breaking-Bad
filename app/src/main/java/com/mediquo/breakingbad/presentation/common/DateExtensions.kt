package com.mediquo.breakingbad.presentation.common

import java.text.SimpleDateFormat
import java.util.*

fun Date.yearsUntilNow(currentDate: Date = Calendar.getInstance().time): Int {
    val format = SimpleDateFormat("yyyyMMdd", Locale.US)
    val old = Integer.parseInt(format.format(this.time))
    val now = Integer.parseInt(format.format(currentDate))
    return (now - old) / 10000
}