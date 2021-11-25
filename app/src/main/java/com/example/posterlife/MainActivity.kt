package com.example.posterlife

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.posterlife.UI.BundNavBar
import com.example.posterlife.UI.Navigation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jetpack.camera.ui.theme.CameraTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.posterlife.UI.CamerOpen
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File


class MainActivity : ComponentActivity() {
    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetupStart()





            CameraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))

                        val cameraPermissionState = rememberPermissionState(
                            permission = Manifest.permission.CAMERA)

                        Button(
                            onClick = {
                                cameraPermissionState.launchPermissionRequest()
                            }
                        ) {
                            Text(text = "Permission")
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        CamerOpen(getDirectory())
                    }
                }
            }
        }
    }
    private fun getDirectory():File {
        val mediaDir=externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }
}








@Composable
fun SetupStart() {
    val navController = rememberNavController()

    BundNavBar(navController)
    Navigation(navController)

}

