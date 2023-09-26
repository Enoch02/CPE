@file:OptIn(
    ExperimentalPagerApi::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class,
    ExperimentalPagerApi::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class
)

package com.cpe.ui.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cpe.ui.screens.more.ConfigViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScheduleScreen(
    modifier: Modifier,
    scope: CoroutineScope,
    scheduleViewModel: ScheduleViewModel = hiltViewModel(),
    configViewModel: ConfigViewModel = viewModel()
) {
    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val level by configViewModel.getLevelValue(context).collectAsState(initial = "300")
    scheduleViewModel.selectedLevel.value = level


    LaunchedEffect(key1 = scheduleViewModel.today) {
        pagerState.animateScrollToPage(scheduleViewModel.today)
    }

    Column(modifier = modifier) {
        ScrollableTabRow(selectedTabIndex = pagerState.currentPage) {
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
                ScheduleView(
                    state = rememberLazyListState(),
                    day = dayInt.toString(),
                    level = scheduleViewModel.selectedLevel.value!!
                )
            }
        )
    }
}

@Composable
fun ScheduleView(
    state: LazyListState,
    day: String,
    level: String,
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
    val timeTables = scheduleViewModel.timeTables
    val classes = scheduleViewModel.filterClasses(
        day = day,
        level = level,
        timeTables = timeTables
    )
    /*val colors = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Blue,
        Color.Cyan
    )*/



    if (classes.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = state,
            content = {
                items(
                    count = classes.size,
                    /*key = { index -> courses[index].courseCode },*/
                    itemContent = { index ->
                        /*ScheduleItem(
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth(),
                            course = classes[index]
                        )*/
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = classes[index].courseCode.orEmpty(),
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                )
                            },
                            leadingContent = {
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primary)
                                        /*.background(colors.random(Random(Date().time.toInt())))*/
                                        .size(30.dp)
                                )
                            },
                            supportingContent = {
                                Column {
                                    Text(
                                        text = classes[index].courseTitle.orEmpty(),
                                        fontSize = MaterialTheme.typography.labelLarge.fontSize,
                                        fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                                    )
                                    Text(
                                        text = classes[index].lecturer.orEmpty(),
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        maxLines = 1,
                                    )
                                }
                            },
                            trailingContent = {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = "Time",
                                        fontSize = MaterialTheme.typography.labelLarge.fontSize,
                                        fontWeight = FontWeight.Bold,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                    )
                                    Text(
                                        text = "${classes[index].startTime} - ${classes[index].endTime}",
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        )
                        Divider(
                            modifier = Modifier
                                .padding(8.dp)
                                .alpha(if (index < classes.size) 1f else 0f)
                        )
                    }
                )
            }
        )
    }

    if (classes.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No classes scheduled for today")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    ScheduleScreen(modifier = Modifier, scope = rememberCoroutineScope())
}