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

    fun loginCred(email: String, password: String) : Boolean {
        var loginSuccess = false
        authentication.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = authentication.currentUser
                    loginSuccess = true
                }
            }
        return loginSuccess
    }

    fun loginSignOut() {
        authentication.signOut()
    }


    //TODO This shit ain't working like it should
    @Composable
    fun ToastError() {
        Column(
            content = { Toast.makeText(LocalContext.current, "Login Fejlet.", Toast.LENGTH_SHORT).show()}
        )

    }

}