package com.example.news.data.repository

import com.example.news.data.remote.services.NewsApiService

class NewsRepository {
   suspend fun getNews(countryCode: String, newsPage: Int)=
        NewsApiService.api.getNews(countryCode)

   suspend fun searchNews(searchQuery: String, newsPage: Int)=
        NewsApiService.api.searchNews(searchQuery )
}