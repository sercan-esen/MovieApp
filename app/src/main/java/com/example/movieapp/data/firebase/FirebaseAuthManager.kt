package com.example.movieapp.data.firebase

import com.example.movieapp.domain.firebase.AFirebaseAuthManager

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
}