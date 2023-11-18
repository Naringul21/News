package com.example.news.data.local.modul

import android.content.Context
import androidx.room.Room
import com.example.news.data.local.dao.NewsDao
import com.example.news.data.local.dao.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModul {

    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java, "newsdatabase"
        ).build()


    @Singleton
    @Provides
    fun provideFavDao(newsDB: NewsDatabase): NewsDao = newsDB.newsDao()
}