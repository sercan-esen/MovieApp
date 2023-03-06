package com.example.movieapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            btnLoginLoginScreen.setOnClickListener {
                signInEmailAndPassword()
            }
        }

    }

    private fun navToEntryFragment() {
        findNavController().popBackStack()
    }

    private fun navToResetPasswordFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
    }

    private fun signInEmailAndPassword() {
        val email = binding.etEmailAddressLoginScreen.text.toString().trim()
        val password = binding.etPasswordLoginScreen.text.toString().trim()

        viewModel.signIn(email = email, password = password, onSuccess = ::navigateToHomeFragment, onFailure = {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun navigateToHomeFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}