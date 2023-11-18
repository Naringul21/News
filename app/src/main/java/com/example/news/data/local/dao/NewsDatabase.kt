package com.example.news.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news.data.local.entities.SavedNews

@Database(entities = [SavedNews::class], version = 1)
abstract class NewsDatabase :RoomDatabase() {

    abstract fun newsDao(): NewsDao


}
