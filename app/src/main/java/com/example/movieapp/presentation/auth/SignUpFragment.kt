package com.example.movieapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSignUpBinding
import com.example.movieapp.presentation.util.extension.showToastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: AuthViewModel by viewModels()


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
        val email = binding.etEmailAddressSignUpScreen.text.toString().trim()
        val password = binding.etPasswordSignUpScreen.text.toString().trim()

        val privacyAccepted = binding.checkBoxSignUpScreen.isChecked
        if (privacyAccepted) {
            viewModel.signUp(
                email = email,
                password = password,
                onSuccess = ::navigateToHome,
                onFailure = {
                    requireContext().showToastMessage(it)
                })
        } else {
            requireContext().showToastMessage(resources.getString(R.string.txt_confirm_privacy))
        }


    }

    private fun navigateToHome() {
        requireContext().showToastMessage(resources.getString(R.string.txt_account_created))
        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
    }


}