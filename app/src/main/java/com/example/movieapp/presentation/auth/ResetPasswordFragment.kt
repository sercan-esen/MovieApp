package com.example.movieapp.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentResetPasswordBinding
import com.example.movieapp.presentation.util.extension.showToastMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding

    private val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBackResetPasswordScreen.setOnClickListener {
                navToLoginFragment()
            }

            btnNextResetPassword.setOnClickListener {
                passwordResetEmail()
            }
        }
    }

    private fun navToLoginFragment() {
        findNavController().popBackStack()
    }

    private fun passwordResetEmail() {

        val email = binding.etEmailResetScreen.text.toString().trim()

        viewModel.resetPassword(
            email = email,
            onSuccess = {
                requireContext().showToastMessage(resources.getString(R.string.txt_email_send))
            },
            onFailure = {
                requireContext().showToastMessage(it)
            })
    }
}