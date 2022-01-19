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

sealed class AuthenticationSignUp {
    companion object {

        private val authentication = Firebase.auth

        fun SignUpUser(email: String, password: String, navController: NavController) {

            authentication.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        authentication.currentUser
                        navController.navigate(Profil.ProfilUI.rute)
                    }
                }
        }
    }
}