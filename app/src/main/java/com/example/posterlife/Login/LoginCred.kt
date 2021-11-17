package com.example.posterlife.Login


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.withContext

/**
 * @Source https://firebase.google.com/docs/auth/android/password-auth?authuser=0
 */

class LoginCred {


    fun LoginCred(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    //UI update med user her
                }
                else {
                    //UI update et eller andet her
                }
            }
    }

    fun LoginSignOut() {
        Firebase.auth.signOut()
    }


    @Composable
    fun ToastError() {
        Column(
            content = { Toast.makeText(LocalContext.current, "Login Fejlet.", Toast.LENGTH_SHORT).show()}
        )

    }

}