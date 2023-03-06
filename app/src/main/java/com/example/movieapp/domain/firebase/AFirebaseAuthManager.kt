package com.example.movieapp.domain.firebase

import android.app.Activity
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

abstract class AFirebaseAuthManager {
    protected val auth = Firebase.auth

    abstract fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

     abstract fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )


     abstract fun sendPasswordResetEmail(
        email: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

     abstract fun signInWithGoogle(
         activity: Activity,
         oneTapClient: SignInClient,
         onSuccess: (IntentSenderRequest) -> Unit,
         onFailure: (String) -> Unit
     )
}