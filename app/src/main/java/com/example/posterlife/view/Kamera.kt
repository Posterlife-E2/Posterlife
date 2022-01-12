package com.example.posterlife.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.FlipCameraAndroid
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material.icons.sharp.PhotoLibrary
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.posterlife.R
import com.example.posterlife.view.billedRed.BilledRedigering
import com.example.posterlife.view.billedRed.BilledViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.properties.Delegates

/**
 * @Source https://www.devbitsandbytes.com/configuring-camerax-in-jetpack-compose-to-take-picture/
 *
 * Ift. implementeringen af CameraX har vi stort set udlukkende brugt hvordan MK fra Devbitsandbytes har implementeret, og ændret i den
 * så den passer til vores app. Vi har selv tilføjet permissions, da Android ikke selv kunne finde frem til at spørge efter permissions.
 *
 * @Source https://gabrieltanner.org/blog/android-camerax
 * @Source https://developer.android.com/training/camerax/architecture#permissions
 */

sealed class Kamera(val route: String) {

    sealed class CameraUIAction {
        object OnCameraClick : CameraUIAction()
        object OnGalleryViewClick : CameraUIAction()
        object OnSwitchCameraClick : CameraUIAction()
    }

    @SuppressLint("StaticFieldLeak")
    object KameraAccess : Kamera("openKamera") {

        private lateinit var navControllerKamera: NavController

        private lateinit var uriViewModel: BilledViewModel


        //Dele taget fra https://www.devbitsandbytes.com/configuring-camerax-in-jetpack-compose-to-take-picture/
        @SuppressLint("StaticFieldLeak")
        @Composable
        fun KameraAccess(
            onImageCaptured: (Uri, Boolean) -> Unit,
            onError: (ImageCaptureException) -> Unit,
            navController: NavController,
            billedViewModel: BilledViewModel
        ) {

            uriViewModel = billedViewModel

            navControllerKamera = navController
            val context = LocalContext.current

            if (hasNoPermissions(context)) {
                requestPermission(context)
            }

            var backKamera by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
            val imageCapture: ImageCapture = remember {
                ImageCapture.Builder().build()
            }

            val billedGallery = rememberLauncherForActivityResult(
                ActivityResultContracts.GetContent()

            ) { uri: Uri? ->
                if (uri != null) onImageCaptured(uri, true)
            }



            CameraPreviewView(
                imageCapture,
                backKamera
            ) { cameraUIAction ->
                when (cameraUIAction) {
                    is CameraUIAction.OnCameraClick -> {
                        imageCapture.takePicture(context, backKamera, onImageCaptured, onError)
                    }
                    is CameraUIAction.OnSwitchCameraClick -> {
                        backKamera =
                            if (backKamera == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                            else
                                CameraSelector.LENS_FACING_BACK
                    }
                    is CameraUIAction.OnGalleryViewClick -> {
                        if (true == context.getOutputDirectory().listFiles()?.isNotEmpty()) {
                            billedGallery.launch("image/")
                        }
                    }
                }
            }
        }

        //Taget fra https://www.devbitsandbytes.com/configuring-camerax-in-jetpack-compose-to-take-picture/
        @SuppressLint("RestrictedApi")
        @Composable
        private fun CameraPreviewView(
            imageCapture: ImageCapture,
            lensFacing: Int = CameraSelector.LENS_FACING_BACK,
            cameraUIAction: (CameraUIAction) -> Unit
        ) {

            val context = LocalContext.current
            val lifecycleOwner = LocalLifecycleOwner.current

            val preview = Preview.Builder().build()
            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()

            val previewView = remember { PreviewView(context) }
            LaunchedEffect(lensFacing) {
                val cameraProvider = context.getCameraProvider()
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
                preview.setSurfaceProvider(previewView.surfaceProvider)
            }

            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView({ previewView }, modifier = Modifier.fillMaxSize()) {

                }
                Row(
                    Modifier
                        .background(Black.copy(alpha = 0.5f))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    IconButton(
                        onClick = {
                           navControllerKamera.popBackStack()
                        }){
                        Icon(Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            Modifier.size(46.dp),
                            tint = White)
                    }
                    CameraControl(
                        Icons.Sharp.FlipCameraAndroid,
                        R.string.icn_camera_view_switch_camera_content_description,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(horizontal = 5.dp),
                        onClick = { cameraUIAction(CameraUIAction.OnSwitchCameraClick) }
                    )
                }
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    CameraControls(cameraUIAction)
                }

            }
        }

        //Taget fra https://www.devbitsandbytes.com/configuring-camerax-in-jetpack-compose-to-take-picture/
        suspend fun Context.getCameraProvider(): ProcessCameraProvider =
            suspendCoroutine { continuation ->
                ProcessCameraProvider.getInstance(this).also { cameraProvider ->
                    cameraProvider.addListener({
                        continuation.resume(cameraProvider.get())
                    }, ContextCompat.getMainExecutor(this))
                }
            }

        //Taget fra https://www.devbitsandbytes.com/configuring-camerax-in-jetpack-compose-to-take-picture/
        @Composable
        fun CameraControls(cameraUIAction: (CameraUIAction) -> Unit) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(16.dp)
                    .height(70.dp),
                horizontalArrangement = Arrangement.End,
                //verticalAlignment = Alignment.CenterVertically
            ) {

                CameraControl(
                    Icons.Sharp.Lens,
                    R.string.icn_camera_view_camera_shutter_content_description,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape),
                    onClick = { cameraUIAction(CameraUIAction.OnCameraClick)
                    }
                )
                Spacer(modifier = Modifier.padding(47.dp))
                CameraControl(
                    Icons.Sharp.PhotoLibrary,
                    R.string.icn_camera_view_view_gallery_content_description,
                    modifier = Modifier
                        .size(64.dp),
                    onClick = { cameraUIAction(CameraUIAction.OnGalleryViewClick) }
                )

            }
        }

        //Taget fra https://www.devbitsandbytes.com/configuring-camerax-in-jetpack-compose-to-take-picture/
        @Composable
        fun CameraControl(
            imageVector: ImageVector,
            contentDescId: Int,
            modifier: Modifier = Modifier,
            onClick: () -> Unit
        ) {


            IconButton(
                onClick = onClick,
                modifier = modifier
            ) {
                Icon(
                    imageVector,
                    contentDescription = stringResource(id = contentDescId),
                    modifier = modifier,
                    tint = Color.White
                )
            }

        }

        private const val FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val PHOTO_EXTENSION = ".jpg"


        fun ImageCapture.takePicture(
            context: Context,
            lensFacing: Int,
            onImageCaptured: (Uri, Boolean) -> Unit,
            onError: (ImageCaptureException) -> Unit
        ) {
            val outputDirectory = context.getOutputDirectory()
            // Create output file to hold the image
            val photoFile = createFile(outputDirectory, FILENAME, PHOTO_EXTENSION)
            val outputFileOptions = getOutputFileOptions(lensFacing, photoFile)

            this.takePicture(
                outputFileOptions,
                Executors.newSingleThreadExecutor(),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = output.savedUri ?: Uri.fromFile(photoFile)


                        val kameraThreadExecutor = ContextCompat.getMainExecutor(context)

                        kameraThreadExecutor.execute{
                            uriViewModel.setBilledURI(savedUri)
                            navControllerKamera.navigate(BilledRedigering.BilledConfirm.rute)
                        }


                    }

                    override fun onError(exception: ImageCaptureException) {
                        onError(exception)
                    }
                })
        }


        private fun getOutputFileOptions(
            lensFacing: Int,
            photoFile: File
        ): ImageCapture.OutputFileOptions {

            // Setup image capture metadata
            val metadata = ImageCapture.Metadata().apply {
                // Mirror image when using the front camera
                isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
            }
            // Create output options object which contains file + metadata

            return ImageCapture.OutputFileOptions.Builder(photoFile)
                .setMetadata(metadata)
                .build()
        }

        private fun createFile(baseFolder: File, format: String, extension: String) =
            File(
                baseFolder, SimpleDateFormat(format, Locale.US)
                    .format(System.currentTimeMillis()) + extension
            )


        private fun Context.getOutputDirectory(): File {
            val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
                File(it, this.resources.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else this.filesDir
        }

        //Taget fra https://gabrieltanner.org/blog/android-camerax
        private val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        // Taget fra https://gabrieltanner.org/blog/android-camerax
        private fun hasNoPermissions(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        }

        // Taget fra https://gabrieltanner.org/blog/android-camerax
        fun requestPermission(context: Context) {
            ActivityCompat.requestPermissions(context as Activity, permissions, 0)
        }
    }
}