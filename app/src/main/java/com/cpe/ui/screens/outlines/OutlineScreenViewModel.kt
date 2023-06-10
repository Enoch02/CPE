package com.cpe.ui.screens.outlines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.cpe.util.Constants

class OutlineScreenViewModel : ViewModel() {
    val courses = mutableStateListOf<Pair<String, String>>()
    var searchResults: SnapshotStateList<Pair<String, String>> = mutableStateListOf()
    var showDetails by mutableStateOf(false)

    var selectedCourse by mutableStateOf(Pair("", ""))

    /*init {
        courses.addAll(Constants.courses300)
    }*/

    fun filterCourses(query: String) {
        if (searchResults.isNotEmpty()) {
            clearSearchResults()
        }
        courses.forEach { course ->
            if (course.first.contains(query)) {
                searchResults.add(course)
            }
        }
    }

    fun clearSearchResults() {
        if (searchResults.isNotEmpty()) {
            searchResults.clear()
        }
    }

    fun changeLevel(level: String) {
        when (level) {
            "200" -> {
                courses.clear()
                courses.addAll(Constants.courses200)
            }

            "300" -> {
                courses.clear()
                courses.addAll(Constants.courses300)
            }

            "400" -> {
                courses.clear()
                courses.addAll(Constants.courses400)
            }

            "500" -> {
                courses.clear()
                courses.addAll(Constants.courses500)
            }
        }
    }
}