package com.example.movieapp.di

import com.example.movieapp.data.firebase.FirebaseAuthManager
import com.example.movieapp.data.remote.ApiService
import com.example.movieapp.data.repository.HomeRepositoryImp
import com.example.movieapp.domain.firebase.AFirebaseAuthManager
import com.example.movieapp.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule{
    @Provides
    @ViewModelScoped
    fun provideFirebaseAuthManager () : AFirebaseAuthManager = FirebaseAuthManager()

    @Provides
    @ViewModelScoped
    fun provideHomeRepository(apiService: ApiService): HomeRepository = HomeRepositoryImp(apiService = apiService )
}