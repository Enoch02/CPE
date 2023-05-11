@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import com.cpe.ui.screens.HomeScreen
import com.cpe.ui.screens.schedule.ScheduleScreen
import com.cpe.ui.theme.CPETheme
import com.cpe.util.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CPETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(
                        LocalDensity provides Density(
                            density = LocalDensity.current.density,
                            fontScale = 1f // - we set here default font scale instead of system one
                        ),
                        content = {
                            MainScaffold()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScaffold() {
    var currentScreen by rememberSaveable { mutableStateOf(Screens.HOME_SCREEN) }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                        content = {
                            //TODO: Replace
                            Icon(imageVector = Icons.Filled.Person, contentDescription = "profile")
                        }
                    )
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        },
        bottomBar = {
            val screens = Screens.values()
            val icons = listOf(
                Icons.Filled.Home,
                Icons.Filled.CalendarMonth,
                Icons.Filled.Book,
                Icons.Filled.MoreHoriz
            )
            val labels = listOf(
                stringResource(R.string.home),
                stringResource(R.string.schedule),
                stringResource(R.string.resources),
                stringResource(R.string.more)
            )

            NavigationBar {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = screen == currentScreen,
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(imageVector = icons[index], contentDescription = null)
                        },
                        label = {
                            Text(
                                text = labels[index],
                                maxLines = 1,
                                softWrap = true,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        },
        content = { paddingValues ->
            Crossfade(
                targetState = currentScreen,
                content = { _currentScreen ->
                    when (_currentScreen) {
                        Screens.HOME_SCREEN -> {
                            HomeScreen(
                                modifier = Modifier.padding(paddingValues),
                                onViewMoreClicked = { currentScreen = Screens.SCHEDULE_SCREEN }
                            )
                        }

                        Screens.SCHEDULE_SCREEN -> {
                            ScheduleScreen(
                                modifier = Modifier.padding(paddingValues),
                                scope = scope
                            )
                        }

                        Screens.RESOURCES_SCREEN -> {

                        }

                        Screens.MORE_SCREEN -> {

                        }
                    }
                }
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    MainScaffold()
}
