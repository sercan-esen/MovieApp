package com.example.movieapp.presentation.auth

import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.firebase.AFirebaseAuthManager
import com.example.movieapp.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuthManager: AFirebaseAuthManager
) : BaseViewModel() {

    fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) = viewModelScope.launch {
        firebaseAuthManager.signInWithEmailAndPassword(
            email = email,
            password = password,
            onSuccess = {
                onSuccess.invoke()
            },
            onFailure = {
                onFailure.invoke(it)
            })
    }
}