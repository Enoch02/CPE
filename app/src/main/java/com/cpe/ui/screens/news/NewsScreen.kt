@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe.ui.screens.news

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cpe.R
import com.cpe.data.models.News
import com.cpe.ui.composables.NewsItem
import kotlinx.coroutines.launch

@Composable
fun NewsScreen() {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val news = listOf(
        News(
            headline = "Headline",
            content = stringResource(id = R.string.lorem)
        ),
        News(
            headline = "Headline",
            content = stringResource(id = R.string.lorem)
        ),
        News(
            headline = "Headline",
            content = stringResource(id = R.string.lorem)
        ),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (news.size > 1) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            state.animateScrollToItem(state.firstVisibleItemIndex + 1)
                        }
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDownward,
                            contentDescription = "next item"
                        )
                    }
                )
            }
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                state = state,
                content = {
                    items(
                        count = news.size,
                        itemContent = { index ->
                            NewsItem(
                                modifier = Modifier.height(IntrinsicSize.Max),
                                news = news[index]
                            )
                        }
                    )
                }
            )
        }
    )
}