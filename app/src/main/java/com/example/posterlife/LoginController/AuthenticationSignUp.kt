package com.example.posterlife.LoginController

import androidx.navigation.NavController
import com.example.posterlife.UI.Profil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

sealed class AuthenticationSignUp {
    companion object {

        private lateinit var authentication: FirebaseAuth

        fun SignUpUser(email: String, password: String, navController: NavController) {

            authentication = Firebase.auth

            authentication.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        authentication.currentUser
                        navController.navigate(Profil.ProfilUI.rute)
                        //UpdateUI something here.
                    } else {
                    }
                    //Update UI something here

                }
        }
    }
}