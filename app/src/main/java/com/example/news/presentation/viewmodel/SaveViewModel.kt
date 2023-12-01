package com.example.news.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.local.entities.SavedNews
import com.example.news.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    var savedNews = MutableLiveData<List<SavedNews>>()
    fun getSavedNews() =   CoroutineScope(Dispatchers.IO).launch {
        repository.getSavedNews()
        Log.d("TEST ROOM RESULT", "Success")


    }
    fun deleteNews(savedNews: SavedNews)=viewModelScope.launch {
            repository.deleteNews(savedNews)
        }

}