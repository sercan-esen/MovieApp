package com.example.movieapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.data.model.response.CategoriesItem
import com.example.movieapp.data.model.response.MoviesItem
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.presentation.util.CategoriesHelper
import com.example.movieapp.presentation.util.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeCategoryAdapter: HomeCategoryAdapter
    private lateinit var moviesAdapter: MostPopularMoviesAdapter

    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initAdapters()
        viewModel.getCategories()
        viewModel.getUpcomingMovies()
    }

    private fun initObservers() {
        observe(viewModel.categoriesLiveData) {
            CategoriesHelper.setCategoriesList(it.genres)
            homeCategoryAdapter.updateList(it.genres)
        }
        observe(viewModel.moviesLiveData) {
            moviesAdapter.updateList(it.results)
            binding.rvMostPopular.post {
                binding.rvMostPopular.smoothScrollToPosition(0)
            }
        }
        observe(viewModel.upcomingMoviesLiveData) {
            //TODO init UI
            Log.e("DATA:", "$it")
        }
        observe(viewModel.selectedCategoriesId) {
            viewModel.getMoviesByCategoriesId(it)
        }
    }

    private fun initAdapters() {
        homeCategoryAdapter = HomeCategoryAdapter(requireContext(), ::onCategoriesSelected)
        binding.rvCategories.adapter = homeCategoryAdapter

        moviesAdapter = MostPopularMoviesAdapter(requireContext(), ::onMovieSelected)
        binding.rvMostPopular.adapter = moviesAdapter
    }


    private fun onCategoriesSelected(item: CategoriesItem) {
        viewModel.selectedCategoriesId.value = item.id
    }

    private fun onMovieSelected(movieItem: MoviesItem) {
        //TODO navigate to movie detail page
    }
}