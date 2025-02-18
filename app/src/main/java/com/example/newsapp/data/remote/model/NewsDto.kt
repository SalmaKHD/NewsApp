package com.example.newsapp.data.remote.model

import androidx.annotation.Keep
import com.example.newsapp.ui.model.NewsUiModel
import com.google.gson.annotations.SerializedName

@Keep
data class NewsDto(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String?
)

fun Article.toUiModel(): NewsUiModel.NewsItemUiModel {
    return NewsUiModel.NewsItemUiModel(
        id = this.hashCode(), // Using a hash code as a temporary ID, ideally you should have a unique ID
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = this.source?.name,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}
