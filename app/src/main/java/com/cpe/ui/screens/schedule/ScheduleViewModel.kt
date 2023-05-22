package com.cpe.ui.screens.schedule

import android.content.Context
import android.icu.util.Calendar
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpe.data.models.Course
import com.cpe.data.models.TimeTable
import com.cpe.data.repository.FirebaseRepository
import com.cpe.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: FirebaseRepository) :
    ViewModel() {
    private val classes = mutableStateListOf<Course>()

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
    val levels = Constants.levels
    var selectedLevel: MutableState<String?> = mutableStateOf(null)
    val timeTables = mutableStateListOf<TimeTable>()

    init {
        getData()
    }

    fun getClasses(level: String) {
        val table = timeTables.filter { level == it.level }
        if (table.isNotEmpty()) {
            classes.addAll(table.first().classes!!)
        }
    }

    fun filterClasses(day: String, level: String, timeTables: List<TimeTable>): List<Course> {
        val timeTable = timeTables.find { it.level == level }

        if (timeTable != null) {
            return timeTable.classes!!.filter { it.dayOfWeek == day }
        }

        return emptyList()
    }

    private fun getData() {
        viewModelScope.launch {
            val data = repository.getData()
            Log.d("TAG", "getData: $data")
            timeTables.addAll(data)
        }
    }

    fun checkForDataUpdate(context: Context) {
        viewModelScope.launch {
            val temp = repository.getData()

            if (temp == timeTables) {
                Toast.makeText(context, "No changes are available", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                timeTables.clear()
                timeTables.addAll(temp)
            }
        }
    }
}