@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe.ui.screens.outlines

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cpe.ui.screens.more.ConfigViewModel
import com.cpe.util.Constants

@Composable
fun OutlineScreen(
    navController: NavController,
    outlineViewModel: OutlineScreenViewModel = viewModel(),
    configViewModel: ConfigViewModel = viewModel()
) {
    val context = LocalContext.current
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val courses = outlineViewModel.courses
    val searchResults = outlineViewModel.searchResults
    val level by configViewModel.getLevelValue(context).collectAsState(initial = "300")
    outlineViewModel.changeLevel(level)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            var showFilterDropdown by remember { mutableStateOf(false) }

            AnimatedVisibility(visible = !active) {
                TopAppBar(
                    title = {
                        val titleText = if (outlineViewModel.showDetails)
                            outlineViewModel.selectedCourse.first
                        else "Course Outlines"

                        Text(text = titleText)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                if (outlineViewModel.showDetails) {
                                    outlineViewModel.showDetails = false
                                } else {
                                    navController.popBackStack()
                                }
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Navigate back"
                                )
                            }
                        )
                    },
                    actions = {
                        if (!outlineViewModel.showDetails) {
                            IconButton(
                                onClick = { showFilterDropdown = true },
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.FilterList,
                                        contentDescription = "Filter"
                                    )

                                    DropdownMenu(
                                        expanded = showFilterDropdown,
                                        onDismissRequest = { showFilterDropdown = false },
                                        content = {
                                            Constants.levels.forEach { level ->
                                                DropdownMenuItem(
                                                    text = { Text(text = "$level Level") },
                                                    onClick = {
                                                        outlineViewModel.changeLevel(level)
                                                        showFilterDropdown = false
                                                    },
                                                )
                                            }
                                        }
                                    )
                                }
                            )
                        }
                    }
                )
            }
        },
        content = { paddingValues ->
            AnimatedVisibility(visible = !outlineViewModel.showDetails) {
                Column(modifier = Modifier.padding(paddingValues)) {
                    SearchBar(
                        query = query,
                        onQueryChange = {
                            query = it.uppercase()
                            outlineViewModel.filterCourses(query)
                        },
                        onSearch = {
                            outlineViewModel.filterCourses(query)
                        },
                        active = active,
                        onActiveChange = {
                            active = it
                            outlineViewModel.clearSearchResults()
                        },
                        placeholder = { Text(text = "Course code") },
                        shape = OutlinedTextFieldDefaults.shape,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search Icon"
                            )
                        },
                        trailingIcon = {
                            if (active) {
                                IconButton(
                                    onClick = {
                                        if (query.isNotEmpty()) {
                                            query = ""
                                        } else {
                                            active = false
                                        }
                                    },
                                    content = {
                                        Icon(
                                            imageVector = Icons.Filled.Close,
                                            contentDescription = "Search Icon"
                                        )
                                    }
                                )
                            }
                        },
                        content = {
                            LazyColumn(
                                content = {
                                    items(
                                        count = searchResults.size,
                                        itemContent = { index ->
                                            ListItem(
                                                headlineContent = { Text(text = searchResults[index].first) },
                                                supportingContent = { Text(text = searchResults[index].second) },
                                                modifier = Modifier.clickable {
                                                    outlineViewModel.selectedCourse =
                                                        searchResults[index]
                                                    outlineViewModel.showDetails = true
                                                }
                                            )
                                        }
                                    )
                                }
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        content = {
                            items(
                                count = courses.size,
                                itemContent = { index ->
                                    ListItem(
                                        headlineContent = { Text(text = courses[index].first) },
                                        supportingContent = { Text(text = courses[index].second) },
                                        modifier = Modifier.clickable {
                                            outlineViewModel.selectedCourse = courses[index]
                                            outlineViewModel.showDetails = true
                                        }
                                    )
                                    Divider()
                                }
                            )
                        }
                    )
                }
            }
            AnimatedVisibility(visible = outlineViewModel.showDetails) {
                OutlineDetail(
                    selectedCourse = outlineViewModel.selectedCourse,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    )
}

@Composable
fun OutlineDetail(
    selectedCourse: Pair<String, String>,
    modifier: Modifier,
    outlineViewModel: OutlineScreenViewModel = viewModel()
) {
    val details = Constants.courseDetails[selectedCourse.first]

    BackHandler(
        onBack = {
            outlineViewModel.showDetails = false
            outlineViewModel.selectedCourse = Pair("", "")
        }
    )

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        content = {
            items(
                count = details?.size ?: 0,
                itemContent = { index ->
                    ListItem(
                        headlineContent = { Text(text = details?.get(index) ?: "") },
                    )
                    Divider()
                }
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    OutlineScreen(rememberNavController())
}