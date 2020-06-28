package com.nguyen.basemvvm.utils.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Double.formatCurrency() : String {
    val nf = NumberFormat.getNumberInstance(Locale.getDefault())
    val df = nf as DecimalFormat
    return df.format(this) + "₫"
}