package com.example.movieapp.data.repository

import com.example.movieapp.data.model.response.GetCategoriesResponse
import com.example.movieapp.data.model.response.GetUpcomingMoviesResponse
import com.example.movieapp.data.model.response.MoviesResponse
import com.example.movieapp.data.remote.ApiService
import com.example.movieapp.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val apiService: ApiService): HomeRepository {
    override suspend fun getCategories(): GetCategoriesResponse? {
        return kotlin.runCatching {
           apiService.getCategories()
      }.getOrNull()
    }

    override suspend fun getMoviesByCategoriesId(genreId: Int): MoviesResponse? {
        return kotlin.runCatching {
            apiService.getMoviesByCategoriesId(withGenres = genreId)
        }.getOrNull()
    }

    override suspend fun getUpcomingMovies(): GetUpcomingMoviesResponse? {
        return kotlin.runCatching {
            apiService.getUpcomingMovies()
        }.getOrNull()
    }
}