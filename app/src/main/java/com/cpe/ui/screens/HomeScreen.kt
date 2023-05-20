@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cpe.R
import com.cpe.data.models.News
import com.cpe.ui.composables.NewsItem
import com.cpe.ui.composables.ScheduleItem
import com.cpe.ui.navigation.Screen
import com.cpe.ui.screens.schedule.ScheduleViewModel


@Composable
fun NewHomeScreen(modifier: Modifier, navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
        ) {
            val intent = remember {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://stdportal.oouagoiwoye.edu.ng/index.php")
                )
            }
            GridItem(
                label = "OOU Student Portal",
                icon = painterResource(R.drawable.baseline_school_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            GridItem(
                label = "GPA Calculator",
                icon = painterResource(id = R.drawable.baseline_calculate_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.GpCalculatorScreen.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
        ) {
            GridItem(
                label = "Course Outlines",
                icon = painterResource(id = R.drawable.baseline_menu_book_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.OutlineScreen.route)
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            GridItem(
                label = "News & Events",
                icon = painterResource(id = R.drawable.newspaper),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.NewsScreen.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
        ) {
            GridItem(
                label = "E-Library",
                icon = painterResource(id = R.drawable.baseline_library_books),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    Toast.makeText(context, "Coming soon!", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            GridItem(
                label = "Lecturer Portal",
                icon = painterResource(id = R.drawable.baseline_chat_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .alpha(0f)
            )
        }
    }
}

@Composable
private fun GridItem(
    label: String,
    icon: Painter,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(40.dp)
            )
            Text(
                text = label,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewPreview() {
    NewHomeScreen(modifier = Modifier.fillMaxSize(), rememberNavController())
}

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
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
    val todayClasses = scheduleViewModel.getTodayClasses()

    if (todayClasses.isNotEmpty()) {
        Text(
            text = "Today's Classes",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            maxLines = 1,
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
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))

    val news = listOf(
        News(
            headline = "Headline",
            content = ""
        ),
        News(
            headline = "Headline",
            content = ""
        ),
        News(
            headline = "Headline",
            content = ""
        ),
    )
    val width = (LocalConfiguration.current.screenWidthDp.dp) - 16.dp

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

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier, {})
}*/
