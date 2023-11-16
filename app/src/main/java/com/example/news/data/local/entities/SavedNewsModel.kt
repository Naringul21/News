package com.example.news.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("saved_news")
data class SavedNewsModel(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String,
    val url: String?,
    val urlToImage: String?
){
    @PrimaryKey(autoGenerate = true)
    val uuid : Int=0
}

