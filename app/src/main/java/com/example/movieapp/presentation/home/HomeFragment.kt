package com.example.movieapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.presentation.util.extension.observe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

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
        viewModel.getCategories()
        viewModel.getMoviesByCategoriesId(28)
        viewModel.getUpcomingMovies()
    }

    private fun initObservers() {
        observe(viewModel.categoriesLiveData) {
            //TODO init UI
            Log.e("DATA:","$it")
        }
        observe(viewModel.moviesLiveData) {
            //TODO init UI
            Log.e("DATA:","$it")
        }
        observe(viewModel.upcomingMoviesLiveData) {
            //TODO init UI
            Log.e("DATA:","$it")
        }
    }
}