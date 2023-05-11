package com.cpe.util

import java.text.SimpleDateFormat
import java.util.Locale

fun compareTime(t1: String, t2: String, pattern: String = "H:m a"): Int? {
    val sdf = SimpleDateFormat(pattern, Locale.UK)

    val parsedT1 = sdf.parse(t1)
    val parsedT2 = sdf.parse(t2)

    if (parsedT1 != null && parsedT2 != null)
        return parsedT1.compareTo(parsedT2)

    return null
}