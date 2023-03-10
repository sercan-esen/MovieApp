package com.example.movieapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.response.GetCategoriesResponse
import com.example.movieapp.data.model.response.GetUpcomingMoviesResponse
import com.example.movieapp.data.model.response.MoviesResponse
import com.example.movieapp.domain.repository.HomeRepository
import com.example.movieapp.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    BaseViewModel() {

    private val _categoriesLiveData: MutableLiveData<GetCategoriesResponse> = MutableLiveData()
    val categoriesLiveData = _categoriesLiveData

    private val _moviesLiveData: MutableLiveData<MoviesResponse> = MutableLiveData()
    val moviesLiveData = _moviesLiveData

    private val _upcomingMoviesLiveData: MutableLiveData<GetUpcomingMoviesResponse> = MutableLiveData()
    val upcomingMoviesLiveData = _upcomingMoviesLiveData

    val selectedCategoriesId: MutableLiveData<Int> = MutableLiveData()

    fun getCategories() {
        viewModelScope.launch {
            val response = homeRepository.getCategories()
            response?.let {
                _categoriesLiveData.postValue(it)
            }
        }
    }

    fun getMoviesByCategoriesId(genreId: Int) {
        viewModelScope.launch {
            val response = homeRepository.getMoviesByCategoriesId(genreId = genreId)
            response?.let {
                _moviesLiveData.postValue(it)
            }
        }
    }
    fun getUpcomingMovies(){
        viewModelScope.launch{
            val response = homeRepository.getUpcomingMovies()
            response?.let {
                _upcomingMoviesLiveData.postValue(it)
            }
        }
    }
}