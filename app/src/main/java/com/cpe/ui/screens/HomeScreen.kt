package com.cpe.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cpe.data.models.News
import com.cpe.ui.composables.NewsItem
import com.cpe.ui.composables.ScheduleItem
import com.cpe.ui.screens.schedule.ScheduleViewModel

@Composable
fun HomeScreen(modifier: Modifier, onViewMoreClicked: () -> Unit) {
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TodayClassesView(onViewMoreClicked = onViewMoreClicked)

        Spacer(modifier = Modifier.height(30.dp))

        NewsComposable(listState)
    }
}

@Composable
fun TodayClassesView(
    onViewMoreClicked: () -> Unit,
    scheduleViewModel: ScheduleViewModel = viewModel()
) {
    val todayClasses = scheduleViewModel.getTodayClasses()

    if (todayClasses.isNotEmpty()) {
        Text(
            text = "Today's Classes",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            maxLines = 1,
            softWrap = true,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Column {
            todayClasses.forEach {
                ScheduleItem(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    course = it
                )
            }
            TextButton(
                onClick = onViewMoreClicked,
                content = { Text(text = "View more..") },
                modifier = Modifier.align(Alignment.End)
            )
        }
    }/* else {
        Column(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Filled.Face, contentDescription = null)
            Text(
                text = "No classes today!",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                maxLines = 1,
                softWrap = true,
                overflow = TextOverflow.Ellipsis
            )
        }
    }*/
}

@Composable
fun NewsComposable(listState: LazyListState) {
    Text(
        text = "News",
        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        maxLines = 1,
        softWrap = true,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))

    val news = listOf(
        News(
            headline = "Headline",
            shortDesc = "Short Description",
            content = ""
        ),
        News(
            headline = "Headline",
            shortDesc = "Short Description",
            content = ""
        ),
        News(
            headline = "Headline",
            shortDesc = "Short Description",
            content = ""
        ),
    )
    val width = (LocalConfiguration.current.screenWidthDp.dp) - 8.dp

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
        content = {
            items(
                count = news.size,
                itemContent = { index ->
                    NewsItem(
                        modifier = Modifier
                            .width(width)
                            .height(IntrinsicSize.Max),
                        news = news[index]
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier, {})
}