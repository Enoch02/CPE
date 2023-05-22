@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe.ui.screens.gpcalculator

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cpe.R
import com.cpe.data.models.GpCalcCourse
import com.cpe.ui.composables.GpCalcEntry
import kotlinx.coroutines.launch

@Composable
fun GpCalculatorScreen(
    navController: NavController,
    gpCalcViewModel: GpCalcViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val courses = gpCalcViewModel.courses

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "GPA Calculator") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        content = {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Navigate back"
                            )
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (courses.size > 1) {
                                courses.removeLast()
                            }
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_remove),
                                contentDescription = "Remove Course"
                            )
                        }
                    )

                    IconButton(
                        onClick = {
                            courses.add(GpCalcCourse("", "A", "2"))
                            scope.launch {
                                listState.scrollToItem(courses.lastIndex)
                            }
                        },
                        content = {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Course")
                        }
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Course (optional)",
                        modifier = Modifier
                            .weight(3f)
                            .padding(end = 4.dp)
                    )
                    Text(
                        text = "Grade",
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                    )
                    Text(
                        text = "Unit",
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.8f),
                    state = listState,
                    contentPadding = PaddingValues(bottom = 12.dp),
                    content = {
                        items(
                            count = courses.size,
                            itemContent = { index ->
                                val course = courses[index]

                                GpCalcEntry(
                                    course = course.name,
                                    grade = course.grade,
                                    units = course.units,
                                    modifier = Modifier.padding(bottom = 12.dp),
                                    onCourseChange = { newCourseName ->
                                        courses[index] = course.copy(name = newCourseName)
                                    },
                                    onGradeChange = { newGrade ->
                                        courses[index] = course.copy(grade = newGrade)
                                    },
                                    onUnitsChange = { newUnits ->
                                        courses[index] = course.copy(units = newUnits)
                                    }
                                )
                            }
                        )
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = { gpCalcViewModel.calculateGpa() },
                    content = {
                        Text(text = "Calculate")
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                if (gpCalcViewModel.yourGp != null) {
                    Dialog(onDismissRequest = { gpCalcViewModel.yourGp = null }) {
                        val formattedGPA = String.format("%.2f", gpCalcViewModel.yourGp)

                        Text(
                            text = "Your GPA: $formattedGPA",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.displaySmall.fontSize
                        )
                    }
                }

                if (gpCalcViewModel.errorState) {
                    Toast.makeText(context, "Invalid input(s)", Toast.LENGTH_SHORT).show()
                    gpCalcViewModel.errorState = false
                }
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    GpCalculatorScreen(rememberNavController())
}