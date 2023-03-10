package com.example.movieapp.data.remote

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.model.response.GetCategoriesResponse
import com.example.movieapp.data.model.response.GetUpcomingMoviesResponse
import com.example.movieapp.data.model.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    suspend fun getCategories(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): GetCategoriesResponse

    @GET("discover/movie")
    suspend fun getMoviesByCategoriesId(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_genres") withGenres: Int
    ): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String = BuildConfig.API_KEY): GetUpcomingMoviesResponse
}