package com.example.news.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.local.entities.SavedNews
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSaved(saved_news:SavedNews)

    @Query("SELECT * FROM savednews")
     fun getSavedNews(): List<SavedNews>

    @Delete
    fun deleteNews(saved_news: SavedNews)






}