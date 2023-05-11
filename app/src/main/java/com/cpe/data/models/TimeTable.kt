package com.cpe.data.models

data class TimeTable(
    val semester: String,
    val level: String,
    val classes: List<Course>
)
