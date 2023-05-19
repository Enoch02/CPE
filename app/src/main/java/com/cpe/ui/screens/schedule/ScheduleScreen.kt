@file:OptIn(ExperimentalPagerApi::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class)

package com.cpe.ui.screens.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cpe.data.models.Course
import com.cpe.ui.composables.ScheduleItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScheduleScreen(
    modifier: Modifier,
    scope: CoroutineScope,
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()
    val listState = rememberLazyListState()

    LaunchedEffect(key1 = scheduleViewModel.today) {
        pagerState.animateScrollToPage(scheduleViewModel.today)
    }

    Column(modifier = modifier) {
        val items = listOf("200", "300", "400", "500")
        var selectedItem by rememberSaveable { mutableStateOf(items[1]) }
        var expanded by remember { mutableStateOf(false) }

        /*Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Level") },
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
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = item
                            expanded = false
                        },
                        content = {
                            Text(text = item)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
*/
        TabRow(selectedTabIndex = pagerState.currentPage) {
            scheduleViewModel.days.forEachIndexed { index, day ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    content = {
                        Text(
                            text = day,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    modifier = Modifier.height(50.dp)
                )
            }
        }

        HorizontalPager(
            count = scheduleViewModel.days.size,
            state = pagerState,
            content = { dayInt ->
                val classesToday by remember { mutableStateOf(scheduleViewModel.filterClasses(dayInt)) }
                ScheduleView(state = listState, classesToday)
            }
        )
    }
}

@Composable
fun ScheduleView(state: LazyListState, classes: List<Course>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = state,
        content = {
            items(
                count = classes.size,
                /*key = { index -> courses[index].courseCode },*/
                itemContent = { index ->
                    ScheduleItem(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth(),
                        course = classes[index]
                    )
                }
            )
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    ScheduleScreen(modifier = Modifier, scope = rememberCoroutineScope())
}