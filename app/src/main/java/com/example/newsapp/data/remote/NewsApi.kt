package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.model.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("v2/everything")
    suspend fun fetchNews(
        @Query("q") query: String,
        @Query("from", encoded = true) fromDate: String,
        @Query("to", encoded = true) toDate: String,
        @Query("sortBy") sortBy: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
    ): NewsDto
}