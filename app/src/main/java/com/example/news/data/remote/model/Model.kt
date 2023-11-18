package com.example.news.data.remote.model

import java.io.Serializable


data class News(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Articles>
)

data class Articles(
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
): Serializable

data class Source(
    val name: String?
) :Serializable
