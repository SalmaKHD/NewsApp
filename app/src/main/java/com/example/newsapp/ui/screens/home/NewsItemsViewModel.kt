package com.example.newsapp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.remote.NewsRemoteDataSource
import com.example.newsapp.ui.model.NewsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsItemsViewModel @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
): ViewModel() {
    // todo: must be fetched from network or db later + return ids are null
    var selectedNewsArticle: NewsUiModel.NewsItemUiModel? by mutableStateOf(null)
        private set

    val newsItems = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 40
        ),
        pagingSourceFactory = { NewsPagingSource(newsRemoteDataSource) }
    ).flow.cachedIn(viewModelScope)


    fun onNewsItemClicked(newsItem: NewsUiModel.NewsItemUiModel) {
        selectedNewsArticle = newsItem
    }
}

