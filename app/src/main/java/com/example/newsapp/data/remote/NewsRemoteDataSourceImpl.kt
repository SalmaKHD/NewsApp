package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.model.NewsDto
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi
): NewsRemoteDataSource {
    override suspend fun fetchNews(
        query: String,
        fromDate: String,
        toDate: String,
        sortBy: String,
        page: Int,
        apiKey: String
    ): Result<NewsDto> {
        return runCatching { newsApi.fetchNews(query, fromDate = fromDate, toDate = toDate, sortBy, page, apiKey) }
    }
}