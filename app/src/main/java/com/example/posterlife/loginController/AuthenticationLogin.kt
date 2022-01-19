package com.example.posterlife.loginController

import androidx.navigation.NavController
import com.example.posterlife.view.profilUI.Profil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * @Author Kristoffer Pedersen s205354
 *
 * @Source https://firebase.google.com/docs/auth
 *
 * Firebase har en god løsning og vi har implementeret en lignende version fra deres dokumentation.
 */

sealed class AuthenticationLogin {

    companion object {

        val authentication = Firebase.auth

        fun signIn(email: String, password: String, navController: NavController) {

            val user = authentication.currentUser
            if(user != null) {
                //Hvis brugeren allerede er logget ind gør x.
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