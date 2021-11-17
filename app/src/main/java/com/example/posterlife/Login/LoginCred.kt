package com.example.posterlife.Login


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * @Source https://firebase.google.com/docs/auth/android/password-auth?authuser=0
 */

class LoginCred {

    private val authentication = Firebase.auth

    fun loginCred(email: String, password: String) {
        authentication.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = authentication.currentUser
                    //UI update med user her
                }
                else {
                    //UI update et eller andet her
                }
            }
    }

    fun loginSignOut() {
        authentication.signOut()
    }


    @Composable
    fun ToastError() {
        Column(
            content = { Toast.makeText(LocalContext.current, "Login Fejlet.", Toast.LENGTH_SHORT).show()}
        )

    }

}