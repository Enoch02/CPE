package com.cpe.ui.screens.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpe.data.models.News
import com.cpe.data.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(private val repository: FirebaseRepository) :
    ViewModel() {
    val newsList = mutableStateListOf<News>()
    var showDetails by mutableStateOf(false)

    var selectedNews by mutableStateOf(News())

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            newsList.addAll(repository.getNews())
        }
    }

    fun refresh() {
        newsList.clear()
        getNews()
    }
}