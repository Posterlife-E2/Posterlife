package com.example.posterlife.loginController

import androidx.navigation.NavController
import com.example.posterlife.ui.profilUI.Profil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

sealed class AuthenticationLogin {

    companion object {

        private lateinit var authentication: FirebaseAuth

        fun signIn(email: String, password: String, navController: NavController) {

            authentication = Firebase.auth

            val user = authentication.currentUser
            if(user != null) {
                //noget med at den bare skal finde getUser og komme videre i sit liv.
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