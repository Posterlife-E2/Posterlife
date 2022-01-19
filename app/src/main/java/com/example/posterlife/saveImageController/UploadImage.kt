package com.example.posterlife.saveImageController

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import androidx.compose.Context
import java.io.File
import com.google.firebase.database.FirebaseDatabase
import com.example.posterlife.model.MineDesignModal
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

/**
 * @Author M-Najib Hebrawi (s181663), Thamara Linnea (s205337), Camilla Bøjden (s205360)
 * https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example
 * https://firebase.google.com/docs/database
 * https://www.geeksforgeeks.org/android-how-to-upload-an-image-on-firebase-storage/
 * https://www.youtube.com/watch?v=MfCiiTEwt3g
 * https://stackoverflow.com/questions/53876728/problem-to-upload-file-from-my-app-to-firebase
 */

sealed class UploadImage {
    companion object {
        private var firebaseStore: FirebaseStorage? = null
        private var storageReference: StorageReference? = null
        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("Images")
        val dataList = ArrayList<String>()
        fun uploadImage(filePath: Uri, context: Context) {
            val file = File(filePath.path).name
            firebaseStore = FirebaseStorage.getInstance()
            storageReference = FirebaseStorage.getInstance().getReference("/Images/$file")
            makeDirectory(context, file)
            val key: String = myRef.key.toString()
            storageReference!!.putFile(filePath)
                .addOnSuccessListener { taskSnapshot ->
                    val obj = MineDesignModal(file, taskSnapshot.uploadSessionUri.toString())
                    myRef.child(key).setValue(obj)
                }
                .addOnFailureListener {
                }
        }

        //Bruges til at slette Firebase billeder når man sletter plakater i MineDesign
        fun deleteImage(fileName: String) {
            val storageRef = Firebase.storage.reference.child("Images/$fileName")
            storageRef.delete().addOnSuccessListener {
            }.addOnFailureListener {
            }
        }


        private fun makeDirectory(context: Context, filePath: String) {

            val file = File(context.getOutputDirectory(), "Files.txt")

            if (file.exists()) {
                file.appendText(",${filePath}")
            } else {
                file.writeText(filePath)
            }
        }

        //Fra https://www.devbitsandbytes.com/configuring-camerax-in-jetpack-compose-to-take-picture/
        fun android.content.Context.getOutputDirectory(): File {
            val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
                File(it, "ImagesFile").apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else this.filesDir
        }
    }
}
