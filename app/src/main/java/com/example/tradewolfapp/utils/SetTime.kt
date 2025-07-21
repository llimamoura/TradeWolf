package com.example.tradewolfapp.utils

import java.util.Calendar

fun setTime(): String {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    when (hour) {
        in 0..11 -> {
            return "Good Morning"
        }
        in 12..16 -> {
            return "Good Afternoon"
        }
        in 17..20 -> {
            return "Good Evening"
        }
        else -> {
            return "Good Night"
        }
    }
}