package com.example.movieapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSignUpScreenSignUp.setOnClickListener {
                createUserWithEmailAndPassword()
            }

            btnBackSignUpScreen.setOnClickListener {
                navToEntryFragment()
            }
        }
    }

    private fun navToEntryFragment() {
        findNavController().popBackStack()
    }

    private fun createUserWithEmailAndPassword() {
        auth = Firebase.auth
        val email = binding.etEmailAddressSignUpScreen.text.toString().trim()
        val password = binding.etPasswordSignUpScreen.text.toString().trim()

        val privacyAccepted = binding.checkBoxSignUpScreen.isChecked
        if (privacyAccepted) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Account created", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(requireContext(), "You need confirm Privacy", Toast.LENGTH_SHORT).show()
        }


    }


}