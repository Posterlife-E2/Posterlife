package com.example.posterlife.saveImageController

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import androidx.compose.Context
import java.io.File
import com.google.firebase.database.FirebaseDatabase
import com.example.posterlife.model.MineDesignModal
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.database.Query
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.database.DatabaseReference
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

/**
 * https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example
 * https://firebase.google.com/docs/database
 * https://www.geeksforgeeks.org/android-how-to-upload-an-image-on-firebase-storage/
 * https://www.youtube.com/watch?v=MfCiiTEwt3g
 * https://stackoverflow.com/questions/40581930/how-to-upload-an-image-to-firebase-storage
 * https://www.youtube.com/watch?v=rfdQHOB3jCU
 * https://stackoverflow.com/questions/53876728/problem-to-upload-file-from-my-app-to-firebase
 */

sealed class UploadImage{
    companion object {
        private var firebaseStore: FirebaseStorage? = null
        private var storageReference: StorageReference? = null
        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("Images")
        val dataList = ArrayList<String>()
        fun uploadImage(filePath: Uri,context:Context) {

            val file = File(filePath.path).name;

            firebaseStore = FirebaseStorage.getInstance()
            storageReference = FirebaseStorage.getInstance()
                .getReference("/Images/$file")

            if (filePath != null) {
                makeDirectory(context, "$file")
                var key:String= myRef.key.toString();
                storageReference!!.putFile(filePath)
                    .addOnSuccessListener {taskSnapshot -> ;
                        val obj:MineDesignModal= MineDesignModal(file, taskSnapshot.uploadSessionUri.toString())
                        myRef.child(key).setValue(obj)
                    }
                    .addOnFailureListener {
                    }
            } else {
                Log.d("Error", "An Error Occurred")
            }
        }
        fun DeleteImage (fileName:String){
            firebaseStore = FirebaseStorage.getInstance()
            val url = firebaseStore!!.reference.child("Images/$fileName").downloadUrl.toString();
            val photoRef: StorageReference = firebaseStore!!.getReferenceFromUrl(url)
            photoRef.delete().addOnSuccessListener( OnSuccessListener<Void?> {
                fun onSuccess(aVoid: Void?) {
                    Log.d("Message", "onSuccess: deleted file")
                }
            }).addOnFailureListener(OnFailureListener { // Uh-oh, an error occurred!
                Log.d("Message", "onFailure: did not delete file")
            })

        }
        fun makeDirectory(context: Context,filePath:String){
            var file = File(context.getOutputDirectory(),"Files.txt")
            if(file.exists()){
                file.appendText(",${filePath}")
            }else{
                file.writeText(filePath)
            }
        }
        fun android.content.Context.getOutputDirectory(): File {
            val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
                File(it, "ImagesFile").apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else this.filesDir
        }

    }



}
