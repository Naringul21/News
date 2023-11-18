package com.example.news.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedNews(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "author_name") val author: String?,
    @ColumnInfo(name = "content_text") val content: String?,
    @ColumnInfo(name = "description_text") val description: String?,
    @ColumnInfo(name = "publishedAt_data") val publishedAt: String?,
    @ColumnInfo(name = "title_text") val title: String,
    @ColumnInfo(name = "url_news") val url: String?,
    @ColumnInfo(name = "url_image") val urlToImage: String?
)



