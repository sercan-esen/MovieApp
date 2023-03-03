package com.example.movieapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

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
        auth = Firebase.auth
        val email = binding.etEmailAddressLoginScreen.text.toString().trim()
        val password = binding.etPasswordLoginScreen.text.toString().trim()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }

    }
}