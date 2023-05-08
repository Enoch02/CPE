@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cpe.ui.screens.HomeScreen
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
                    MainScaffold()
                }
            }
        }
    }
}

@Composable
fun MainScaffold() {
    var currentScreen by rememberSaveable { mutableStateOf(Screens.HOME_SCREEN) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { },
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

            NavigationBar {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = screen == currentScreen,
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(imageVector = icons[index], contentDescription = null)
                        }
                    )
                }
            }
        },
        content = { paddingValues ->

            when (currentScreen) {
                Screens.HOME_SCREEN -> {
                    HomeScreen(modifier = Modifier.padding(paddingValues))
                }

                Screens.SCHEDULE_SCREEN -> {

                }

                Screens.RESOURCES_SCREEN -> {

                }

                Screens.MORE_SCREEN -> {

                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    MainScaffold()
}
