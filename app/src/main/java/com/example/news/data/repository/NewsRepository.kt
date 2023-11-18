package com.example.news.data.repository

import com.example.news.data.local.dao.NewsDao
import com.example.news.data.local.dao.NewsDatabase
import com.example.news.data.local.entities.SavedNews
import com.example.news.data.remote.services.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsDao: NewsDao){


    suspend fun getNews(countryCode: String)=
        NewsApiService.api.getNews(countryCode)

   suspend fun searchNews(searchQuery: String)=
        NewsApiService.api.searchNews(searchQuery )

    suspend fun addSaved(savedNews: SavedNews) = newsDao.addSaved(savedNews)
     fun getSavedNews() = newsDao.getSavedNews()

     fun deleteArticle(savedNews: SavedNews) =newsDao.deleteNews(savedNews)
}