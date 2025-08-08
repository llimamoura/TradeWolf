package com.example.tradewolfapp.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Double.formatCryptoValue(): String {
    val formatter = DecimalFormat().apply {
        decimalFormatSymbols = DecimalFormatSymbols(Locale.US)
        isGroupingUsed = true
        maximumFractionDigits = 2
        minimumFractionDigits = 2
        groupingSize = 3
    }

    return "$${formatter.format(this)}"
}