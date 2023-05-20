package com.cpe.ui.screens.gpcalculator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cpe.data.models.GpCalcCourse

const val TAG = "GpCalcViewModel"

class GpCalcViewModel : ViewModel() {
    val courses = mutableStateListOf(GpCalcCourse("", "A", "2"))
    var yourGp: Double? by mutableStateOf(null)
    var errorState by mutableStateOf(false)

    fun calculateGpa() {
        var totalCredits = 0
        var totalGradePoints = 0.0

        try {
            courses.forEach { course ->
                val gradePoint = when (course.grade) {
                    "A" -> 5.0
                    "B" -> 4.0
                    "C" -> 3.0
                    "D" -> 2.0
                    "E" -> 1.0
                    else -> 0.0
                }

                totalGradePoints += gradePoint * course.units.toInt()
                totalCredits += course.units.toInt()
            }

            yourGp = totalGradePoints / totalCredits
        } catch (e: Exception) {
            Log.d(TAG, "calculateGpa: ${e.message}")
            errorState = true
        }
    }
}