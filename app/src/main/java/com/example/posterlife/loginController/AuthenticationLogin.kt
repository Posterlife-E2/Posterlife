package com.example.posterlife.loginController

import androidx.navigation.NavController
import com.example.posterlife.view.profilUI.Profil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

sealed class AuthenticationLogin {

    companion object {

        lateinit var authentication: FirebaseAuth

        fun signIn(email: String, password: String, navController: NavController) {

            authentication = Firebase.auth

            val user = authentication.currentUser
            if(user != null) {
                //Tjek om den allerede er logget ind.
                return
            }

            authentication.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        authentication.currentUser
                        navController.navigate(Profil.ProfilUI.rute)
                    }
                }
        }

        fun loginSignOut() {
            authentication.signOut()
        }
    }



}