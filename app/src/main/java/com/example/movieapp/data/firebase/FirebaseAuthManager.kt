package com.example.movieapp.data.firebase

import android.app.Activity
import android.content.IntentSender
import androidx.activity.result.IntentSenderRequest
import com.example.movieapp.R
import com.example.movieapp.domain.firebase.AFirebaseAuthManager
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient

class FirebaseAuthManager : AFirebaseAuthManager() {
    override fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
       auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
           onSuccess.invoke()
       }.addOnFailureListener {
           onFailure.invoke(it.localizedMessage.orEmpty())
       }
    }

    override fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            onSuccess.invoke()
        }.addOnFailureListener {
            onFailure.invoke(it.localizedMessage.orEmpty())
        }
    }

    override fun sendPasswordResetEmail(
        email: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email).addOnSuccessListener {
            onSuccess.invoke()
        }.addOnFailureListener {
            onFailure.invoke(it.localizedMessage.orEmpty())
        }
    }

    override fun signInWithGoogle(
        activity: Activity,
        oneTapClient: SignInClient,
        onSuccess: (IntentSenderRequest) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(activity.getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(activity) { result ->
                try {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(result.pendingIntent.intentSender)
                            .build()
                   // onSuccess(intentSenderRequest)
                    onSuccess.invoke(intentSenderRequest)
                } catch (e: IntentSender.SendIntentException) {
                    onFailure("Couldn't start One Tap UI: ${e.message}")
                }
            }
            .addOnFailureListener {
                //onFailure(it.message.orEmpty())
                onFailure.invoke(it.message.orEmpty())
            }
    }
}