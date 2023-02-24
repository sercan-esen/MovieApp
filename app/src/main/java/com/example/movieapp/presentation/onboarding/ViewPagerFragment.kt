package com.example.movieapp.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentViewPagerBinding
import com.example.movieapp.presentation.onboarding.screens.FirstScreen
import com.example.movieapp.presentation.onboarding.screens.SecondScreen
import com.example.movieapp.presentation.onboarding.screens.ThirdScreen


class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()

            )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val viewPager = binding.viewPagerOnBoarding
        viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(viewPager)

        return binding.root
    }

}