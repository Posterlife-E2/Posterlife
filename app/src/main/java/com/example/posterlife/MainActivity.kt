package com.example.posterlife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.posterlife.UI.BundNavBar
import com.example.posterlife.UI.Navigation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jetpack.camera.ui.theme.CameraTheme
import java.io.File


class MainActivity : ComponentActivity() {

    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetupStart()
        }
    }


    fun getDirectory():File {
        val mediaDir=externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }
}








@ExperimentalPermissionsApi
@Composable
fun SetupStart() {
    val navController = rememberNavController()

    BundNavBar(navController)
    Navigation(navController)

}

