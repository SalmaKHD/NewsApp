package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.model.NewsDto


interface NewsRemoteDataSource {
    suspend fun fetchNews(
        query: String,
        fromDate: String,
        toDate: String,
        sortBy: String,
        page: Int,
        apiKey: String
    ): Result<NewsDto>
}