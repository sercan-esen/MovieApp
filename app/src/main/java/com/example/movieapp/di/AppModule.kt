package com.example.movieapp.di

import android.content.Context
import com.example.movieapp.BaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val SHARED_PREFERENCES_FILE_NAME = "SharedPref"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideSharedPreferences() = BaseApp.applicationContext().getSharedPreferences(
        SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE
    )
}