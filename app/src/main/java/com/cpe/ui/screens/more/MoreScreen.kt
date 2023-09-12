@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe.ui.screens.more

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cpe.R
import com.cpe.ui.navigation.Screen
import com.cpe.ui.screens.schedule.ScheduleViewModel
import com.cpe.util.Constants
import com.cpe.util.getAppVersion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(
    navController: NavController,
    configViewModel: ConfigViewModel = viewModel(),
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = rememberLazyListState()
    val alwaysDark by configViewModel.getDarkModeValue(context).collectAsState(initial = false)
    val level by configViewModel.getLevelValue(context).collectAsState(initial = "300")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                state = state,
                content = {
                    item {
                        ListItem(
                            headlineContent = { Text(text = "Update schedule") },
                            supportingContent = { Text(text = "Check for schedule updates online") },
                            leadingContent = {
                                Icon(
                                    imageVector = Icons.Filled.Update,
                                    contentDescription = "About"
                                )
                            },
                            modifier = Modifier.clickable {
                                scheduleViewModel.checkForDataUpdate(context)
                            }
                        )
                    }

                    item {
                        var showDialog by rememberSaveable { mutableStateOf(false) }

                        ListItem(
                            headlineContent = { Text(text = "Select your level") },
                            supportingContent = { Text(text = "Select default level (current: $level)") },
                            leadingContent = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_school_24),
                                    contentDescription = "About"
                                )
                            },
                            modifier = Modifier.clickable {
                                showDialog = true
                            }
                        )

                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                confirmButton = {},
                                dismissButton = {
                                    TextButton(
                                        onClick = { showDialog = false },
                                        content = {
                                            Text(text = "Cancel")
                                        }
                                    )
                                },
                                text = {
                                    Column {
                                        Constants.levels.forEach {
                                            val onClick = {
                                                configViewModel.switchLevelValue(
                                                    context = context,
                                                    level = it
                                                )
                                                showDialog = false
                                            }

                                            ListItem(
                                                leadingContent = {
                                                    RadioButton(
                                                        selected = it == level,
                                                        onClick = { onClick() }
                                                    )
                                                },
                                                headlineContent = { Text(text = it) },
                                                trailingContent = {

                                                },
                                                modifier = Modifier.clickable { onClick() }
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }

                    item {
                        ListItem(
                            headlineContent = { Text(text = "Dark Scheme") },
                            supportingContent = { Text(text = "Turn dark scheme on permanently") },
                            leadingContent = {
                                Icon(
                                    imageVector = Icons.Filled.DarkMode,
                                    contentDescription = "About"
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = alwaysDark,
                                    onCheckedChange = { configViewModel.switchDarkModeValue(context) })
                            },
                            modifier = Modifier.clickable {
                                configViewModel.switchDarkModeValue(context)
                            }
                        )
                        Divider(modifier = Modifier.padding(horizontal = 8.dp))
                    }

                    item {
                        ListItem(
                            headlineContent = { Text(text = "About") },
                            leadingContent = {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "About"
                                )
                            },
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.AboutScreen.route)
                            }
                        )
                    }

                    item {
                        val version = getAppVersion(context)
                        val alpha by rememberSaveable {
                            mutableStateOf(
                                if (version.first == "" || version.second == 0) 0f else 1f
                            )
                        }

                        ListItem(
                            headlineContent = { Text(text = "App Version") },
                            leadingContent = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_android_24),
                                    contentDescription = "About"
                                )
                            },
                            supportingContent = {
                                Text(text = "version ${version.first}(${version.second})")
                            },
                            modifier = Modifier
                                .alpha(alpha)
                                .clickable {
                                    Toast
                                        .makeText(context, "🙂", Toast.LENGTH_SHORT)
                                        .show()
                                },
                        )
                    }
                }
            )
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    MoreScreen(rememberNavController())
}