@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GpCalcEntry(
    course: String,
    grade: String,
    units: String,
    modifier: Modifier = Modifier,
    onCourseChange: (String) -> Unit = {},
    onGradeChange: (String) -> Unit = {},
    onUnitsChange: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    val grades = listOf("A", "B", "C", "D", "E", "F")

    Row(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = course,
            onValueChange = { onCourseChange(it) },
            modifier = Modifier
                .weight(4f)
                .padding(end = 8.dp),
            maxLines = 1
        )

        Box(
            modifier = Modifier
                .weight(2f)
                .padding(end = 8.dp)
        ) {
            OutlinedTextField(
                value = grade,
                onValueChange = { onGradeChange(it) },
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        val icon = if (expanded)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown

                        Icon(
                            imageVector = icon,
                            contentDescription = "expand"
                        )
                    }
                },
                readOnly = true,
                maxLines = 1,
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                grades.forEach { grade ->
                    DropdownMenuItem(
                        onClick = {
                            onGradeChange(grade)
                            expanded = false
                        },
                        content = {
                            Text(text = grade)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        OutlinedTextField(
            value = units,
            onValueChange = { onUnitsChange(it) },
            modifier = Modifier.weight(1f),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    GpCalcEntry(course = "TST", grade = "A", units = "2")
}