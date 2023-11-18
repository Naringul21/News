package com.example.news.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.local.dao.NewsDatabase
import com.example.news.data.remote.model.News
import com.example.news.data.repository.NewsRepository
import com.example.news.util.BaseViewModel
import com.example.news.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class FeedViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val newsList: MutableLiveData<Resource<News>> = MutableLiveData()

    val searchNewsList: MutableLiveData<Resource<News>> = MutableLiveData()

    init {
        getNews("en")
    }

    fun getNews(countryCode: String) =viewModelScope.launch {
        newsList.postValue(Resource.Loading())
        val response = newsRepository.getNews(countryCode)

        newsList.postValue(handleNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNewsList.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery)
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