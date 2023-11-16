package com.example.news.data.remote.services

import com.example.news.data.remote.model.News
import com.example.news.util.Utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("language") countryCode: String = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<News>


    @GET("everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<News>


}



