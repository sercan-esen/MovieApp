package com.example.movieapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentEntryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryFragment : Fragment() {
    private lateinit var binding: FragmentEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            entrySignUpButton.setOnClickListener {
                navToSignUpFragment()
            }
            tvLoginEntry.setOnClickListener {
                navToLoginFragment()
            }
        }
    }

    private fun navToSignUpFragment() {
        findNavController().navigate(R.id.action_entryFragment_to_signUpFragment)
    }

    private fun navToLoginFragment() {
        findNavController().navigate(R.id.action_entryFragment_to_loginFragment)
    }


}