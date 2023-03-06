package com.example.movieapp.presentation.auth

import android.app.Activity
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.firebase.AFirebaseAuthManager
import com.example.movieapp.presentation.core.BaseViewModel
import com.google.android.gms.auth.api.identity.SignInClient
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

    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) = viewModelScope.launch {
        firebaseAuthManager.createUserWithEmailAndPassword(
            email = email,
            password = password,
            onSuccess = {
                onSuccess.invoke()
            },
            onFailure = {
                onFailure.invoke(it)
            }
        )
    }

    fun resetPassword(
        email: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) = viewModelScope.launch {
        firebaseAuthManager.sendPasswordResetEmail(
            email = email,
            onSuccess = { onSuccess.invoke() },
            onFailure = { onFailure.invoke(it) })
    }

    fun signInGoogle(
        activity: Activity,
        oneTapClient: SignInClient,
        onSuccess: (IntentSenderRequest) -> Unit,
        onFailure: (String) -> Unit
    ) = viewModelScope.launch {
        firebaseAuthManager.signInWithGoogle(
            activity = activity,
            oneTapClient = oneTapClient,
            onSuccess = {onSuccess.invoke(it)},
            onFailure = {onFailure.invoke(it)}
        )
    }
}