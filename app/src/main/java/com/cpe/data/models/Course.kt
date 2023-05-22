package com.cpe.data.models

/**
 * Don't use really long lecturer names
 */
data class Course(
    val courseTitle: String? = null,
    val courseCode: String? = null,
    val dayOfWeek: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val lecturer: String? = null,
)
