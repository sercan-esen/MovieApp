package com.example.movieapp.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnBackLoginScreen.setOnClickListener {
                navToEntryFragment()
            }
            tvForgotPassword.setOnClickListener {
                navToResetPasswordFragment()
            }
        }

    }

    private fun navToEntryFragment(){
        findNavController().navigate(R.id.action_loginFragment_to_entryFragment)
    }

    private fun navToResetPasswordFragment(){
        findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
    }

}