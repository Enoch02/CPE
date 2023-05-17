package com.cpe.ui.screens.schedule

import android.icu.util.Calendar
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpe.data.models.Course
import com.cpe.data.models.TimeTable
import com.cpe.data.repository.FirebaseRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: FirebaseRepository) :
    ViewModel() {
    val days = listOf("Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat")
    val today = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
        Calendar.SUNDAY -> 0
        Calendar.MONDAY -> 1
        Calendar.TUESDAY -> 2
        Calendar.WEDNESDAY -> 3
        Calendar.THURSDAY -> 4
        Calendar.FRIDAY -> 5
        Calendar.SATURDAY -> 6
        else -> 0
    }
    private var timeTable = mutableStateOf(
        TimeTable(
            semester = "",
            level = "",
            classes = emptyList()
        )
    )

    init {
        viewModelScope.launch(Dispatchers.Main) {
            timeTable.value = TimeTable(
                semester = "Rain",
                level = "300",
                classes = listOf(
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEMO 101",
                        dayOfWeek = 1,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "A Lecturer",
                    ),
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEMO 101",
                        dayOfWeek = 1,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "A Lecturer",
                    ),
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEMO 101",
                        dayOfWeek = 1,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "A Lecturer",
                    ),
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEMO 102",
                        dayOfWeek = 2,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "You",
                    ),
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEM0 103",
                        dayOfWeek = 3,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "You",
                    ),
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEMO 112",
                        dayOfWeek = 5,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "Lol",
                    ),
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEMO 112",
                        dayOfWeek = 4,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "Lol",
                    ),
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEMO 112",
                        dayOfWeek = 4,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "Lol",
                    ),
                    Course(
                        courseTitle = "Demo Course",
                        courseCode = "DEMO 112",
                        dayOfWeek = 4,
                        startTime = "8:00 am",
                        endTime = "9:00 am",
                        lecturer = "A Lecturer",
                    ),
                )
            )
        }
    }

    fun filterClasses(day: Int): List<Course> {
        return timeTable.value.classes.filter { it.dayOfWeek == day }
    }

    /**
     * Get only 2 for preview
     */
    fun getTodayClasses(): List<Course> {
        val classes = timeTable.value.classes.filter { it.dayOfWeek == today }
        return when {
            classes.size >= 3 -> classes.subList(0, 2)
            else -> classes
        }
    }
}