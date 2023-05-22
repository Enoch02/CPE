@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe.ui.screens.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cpe.R
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(
    navController: NavController,
    newsScreenViewModel: NewsScreenViewModel = hiltViewModel()
) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val news = newsScreenViewModel.newsList

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "News & Events") },
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
                        onClick = { newsScreenViewModel.refresh() },
                        content = {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Refresh"
                            )
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            if (news.size > 1) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            if (state.canScrollForward)
                                state.animateScrollToItem(state.firstVisibleItemIndex + 1)
                            else
                                state.scrollToItem(0)
                        }
                    },
                    content = {
                        val icon =
                            if (state.canScrollForward) Icons.Filled.ArrowDownward else Icons.Filled.ArrowUpward
                        Icon(
                            imageVector = icon,
                            contentDescription = "next item"
                        )
                    }
                )
            }
        },
        content = { paddingValues ->
            if (news.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    state = state,
                    content = {
                        items(
                            count = news.size,
                            itemContent = { index ->
                                /*NewsItem(
                                    modifier = Modifier.height(IntrinsicSize.Max),
                                    news = news[index]
                                )*/
                                var showDetails by rememberSaveable { mutableStateOf(false) }

                                ListItem(
                                    leadingContent = {
                                        if (!showDetails) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.newspaper),
                                                contentDescription = null,
                                                modifier = Modifier.size(100.dp),
                                            )
                                        }
                                    },
                                    headlineContent = { Text(text = news[index].headline.orEmpty()) },
                                    supportingContent = {
                                        Text(
                                            text = news[index].content.orEmpty(),
                                            maxLines = if (showDetails) Int.MAX_VALUE else 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    },
                                    modifier = Modifier.clickable {
                                        showDetails = !showDetails
                                    },
                                )
                                Divider(modifier = Modifier.padding(horizontal = 8.dp))
                            }
                        )
                    }
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(text = "Nothing for now")
                    }
                )
            }
        }
    )
}