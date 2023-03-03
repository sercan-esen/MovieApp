package com.example.movieapp.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        auth = Firebase.auth
        val sPassword = binding.etResetPasswordResetScreen.text.toString().trim()

        auth.sendPasswordResetEmail(sPassword)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(requireContext(),"Email Send",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_SHORT).show()
                }
            }
    }
}