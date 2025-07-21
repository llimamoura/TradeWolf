package com.example.tradewolfapp.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Double.formatCryptoValue(): String {
    val formatter = DecimalFormat().apply {
        decimalFormatSymbols = DecimalFormatSymbols(Locale.US)
        isGroupingUsed = true
        groupingSize = 3
    }

    return when {
        this >= 1_000 -> {
            formatter.apply { maximumFractionDigits = 2 }
            "$${formatter.format(this)}"
        }
        this >= 0.1 -> {
            formatter.apply { maximumFractionDigits = 4 }
            "$${formatter.format(this)}"
        }
        else -> {
            formatter.apply {
                maximumFractionDigits = 8
                isGroupingUsed = false
            }
            "$${formatter.format(this)}"
        }
    }
}