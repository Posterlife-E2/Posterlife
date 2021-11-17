package com.example.posterlife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.posterlife.Login.LoginCred
import com.example.posterlife.UI.BundNavBar
import com.example.posterlife.UI.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //test
       val Login = LoginCred()
       Login.loginCred("goat@cheese.dk","Testgoat")
        setContent {
            SetupStart()
        }
    }
}

@Composable
fun SetupStart() {
    val navController = rememberNavController()

    BundNavBar(navController)
    Navigation(navController)


}





