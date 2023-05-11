package com.cpe.data.models

/**
 * Don't use really long lecturer names
 */
data class Course(
    val courseTitle: String,
    val courseCode: String,
    val dayOfWeek: Int,
    val startTime: String,
    val endTime: String,
    val lecturer: String,
)
