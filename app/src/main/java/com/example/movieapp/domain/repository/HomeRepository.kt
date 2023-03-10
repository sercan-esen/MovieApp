package com.example.movieapp.domain.repository

import com.example.movieapp.data.model.response.GetCategoriesResponse
import com.example.movieapp.data.model.response.GetUpcomingMoviesResponse
import com.example.movieapp.data.model.response.MoviesResponse

interface HomeRepository {

    suspend fun getCategories(): GetCategoriesResponse?

    suspend fun getMoviesByCategoriesId(genreId: Int): MoviesResponse?

    suspend fun getUpcomingMovies(): GetUpcomingMoviesResponse?
}