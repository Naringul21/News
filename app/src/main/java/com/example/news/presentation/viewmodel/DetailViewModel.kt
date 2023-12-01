package com.example.news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.local.entities.SavedNews
import com.example.news.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val newsRepository: NewsRepository) :ViewModel() {

    fun addSaved(savedNews: SavedNews) = viewModelScope.launch {
        newsRepository.addSaved(savedNews)
    }

     fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteNews(savedNews:SavedNews) = viewModelScope.launch {
        newsRepository.deleteNews(savedNews)
    }


}