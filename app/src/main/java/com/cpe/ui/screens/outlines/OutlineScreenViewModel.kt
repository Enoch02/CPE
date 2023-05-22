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
            if (isSimilarString(query, course.first)) {
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

    /**
     * "Levenshtein distance algorithm"
     */
    private fun isSimilarString(string1: String, string2: String): Boolean {
        val m = string1.length
        val n = string2.length

        // Create a 2D array to store the distances
        val distances = Array(m + 1) { IntArray(n + 1) }

        // Initialize the first row and column of the array
        for (i in 0..m) {
            distances[i][0] = i
        }
        for (j in 0..n) {
            distances[0][j] = j
        }

        // Calculate the minimum distance
        for (i in 1..m) {
            for (j in 1..n) {
                val substitutionCost = if (string1[i - 1] == string2[j - 1]) 0 else 1
                distances[i][j] = minOf(
                    distances[i - 1][j] + 1,         // Deletion
                    distances[i][j - 1] + 1,         // Insertion
                    distances[i - 1][j - 1] + substitutionCost  // Substitution
                )
            }
        }

        // Check if the distance is within a certain threshold
        val threshold = 3  // Set your desired threshold here
        return distances[m][n] <= threshold
    }

}