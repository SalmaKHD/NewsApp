package com.example.newsapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.ui.components.LoadingScreen
import com.example.newsapp.ui.components.home.NewsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    newsItemsViewModel: NewsItemsViewModel,
    navigateToDetailsScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "News App", fontSize = 18.sp) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val newsData = newsItemsViewModel.newsItems.collectAsLazyPagingItems()


            when (newsData.loadState.refresh) {
                is LoadState.Loading -> {
                    LoadingScreen()
                }

                is LoadState.Error -> {
                    LoadingScreen()
                }

                else -> {
                    LazyColumn {
                        items(
                            newsData.itemCount
                        ) { index ->
                            val item = newsData.get(index)
                            item?.let {
                                NewsItem(
                                    item = it,
                                    onItemClick = {
                                        newsItemsViewModel.onNewsItemClicked(item)
                                        navigateToDetailsScreen()
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        item {
                            // determine what happens when fetching new data
                            when (newsData.loadState.append) {
                                is LoadState.Loading -> {
                                    CircularProgressIndicator()
                                }

                                is LoadState.Error -> {
                                    CircularProgressIndicator()
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}