package com.example.movieapp.presentation.auth

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentEntryBinding
import com.example.movieapp.presentation.util.extension.showToastMessage
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryFragment : Fragment() {
    private lateinit var binding: FragmentEntryBinding

    private lateinit var oneTapClient: SignInClient

    private val viewModel: AuthViewModel by viewModels()

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

        oneTapClient = Identity.getSignInClient(requireContext())

        binding.apply {
            ivGoogleLogo.setOnClickListener {
                signInWithGoogle()
            }
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

    private fun signInWithGoogle() {
        viewModel.signInGoogle(
            requireActivity(),
            oneTapClient,
            onSuccess = {
                googleSignInIntentResultLauncher.launch(it)
            },
            onFailure = {
                requireContext().showToastMessage(it)
            }

        )
    }


    private val googleSignInIntentResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result != null && result.resultCode == Activity.RESULT_OK) {
                val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken
                idToken?.let {
                    findNavController().navigate(R.id.action_entryFragment_to_homeFragment)
                }
            }
        }

}