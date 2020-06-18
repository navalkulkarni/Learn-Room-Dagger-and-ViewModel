package com.mindorks.bootcamp.learndagger.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

import com.mindorks.bootcamp.learndagger.MyApplication
import com.mindorks.bootcamp.learndagger.data.local.DatabaseService
import com.mindorks.bootcamp.learndagger.di.ApplicationContext
import com.mindorks.bootcamp.learndagger.di.DatabaseInfo
import com.mindorks.bootcamp.learndagger.di.NetworkInfo

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = "dummy_db"

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion(): Int = 1

    @Provides
    @NetworkInfo
    fun provideApiKey(): String = "SOME_API_KEY"

    @Singleton
    @Provides
    fun provideDatabaseService():DatabaseService = Room.databaseBuilder(
            application,
            DatabaseService::class.java,
            "bootcamp-database-project-db"
    ).build()
}
