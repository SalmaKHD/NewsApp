package com.example.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.model.toUiModel
import com.example.newsapp.ui.model.NewsUiModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsPagingSource(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : PagingSource<Int, NewsUiModel.NewsItemUiModel>() {

    private val queries = listOf("Microsoft", "Apple", "Google", "Tesla")

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsUiModel.NewsItemUiModel> {
        val page = params.key ?: 1
        val defaultSortedBy = "publishedAt"
        val fromDate: String = LocalDateTime.now().minusDays(1).asIsoDateTime()
        val toDate: String = LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).asIsoDateTime()
        val query = queries[(page - 1) % queries.size] // Cycle through the queries in the specified order

        return try {
            // Call the remote data source to fetch news
            val response = newsRemoteDataSource.fetchNews(
                query = query,
                fromDate = fromDate,
                toDate = toDate,
                sortBy = defaultSortedBy, // Sort by the date of publication
                page = page,
                apiKey = BuildConfig.API_KEY
            )

            // If the response is successful, return the result
            if (response.isSuccess) {
                val newsDto = response.getOrNull() ?: return LoadResult.Error(Exception("No data found"))
                val newsItems = newsDto.articles.map { article ->
                    article.toUiModel() // Assuming `toUiModel` maps the data to the UI model
                }

                LoadResult.Page(
                    data = newsItems,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (newsItems.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to fetch news"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsUiModel.NewsItemUiModel>): Int? {
        // Provide the page to be refreshed
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey }
    }

    fun LocalDateTime.asIsoDateTime(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        return this.format(formatter)
    }
}
