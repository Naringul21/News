package com.example.news.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.remote.model.News
import com.example.news.data.repository.NewsRepository
import com.example.news.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class FeedViewModel (): ViewModel() {
    val newsRepository: NewsRepository = NewsRepository()

    val newsList: MutableLiveData<Resource<News>> = MutableLiveData()
    var newsListPage = 1

    val searchNewsList: MutableLiveData<Resource<News>> = MutableLiveData()
    var searchNewsPage = 1

    init {
        getNews("en")
    }

    fun getNews(countryCode: String) = viewModelScope.launch {
        newsList.postValue(Resource.Loading())
        val response = newsRepository.getNews(countryCode, newsListPage)

        newsList.postValue(handleNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNewsList.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNewsList.postValue(handleSearchNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<News>): Resource<News> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }

        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<News>): Resource<News> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }

        }
        return Resource.Error(response.message())
    }

}