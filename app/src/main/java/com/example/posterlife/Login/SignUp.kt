package com.example.posterlife.Login

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp {

    fun SignUpUser(email: String, password: String) {

        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser

                    //UpdateUI something here.
                }
                else {}
                    //Update UI something here

            }
    }
}