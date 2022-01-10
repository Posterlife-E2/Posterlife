package com.example.posterlife.jsonParser

import android.util.Log
import com.example.posterlife.ui.MineDesign
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener


/**
 * https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example
 * https://firebase.google.com/docs/database
 * https://www.geeksforgeeks.org/android-how-to-upload-an-image-on-firebase-storage/
 * https://www.youtube.com/watch?v=MfCiiTEwt3g
 * https://stackoverflow.com/questions/40581930/how-to-upload-an-image-to-firebase-storage
 * https://www.youtube.com/watch?v=rfdQHOB3jCU
 * https://stackoverflow.com/questions/53876728/problem-to-upload-file-from-my-app-to-firebase.
 */
sealed class MineDesignInfo {
    companion object {
        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("Images")
        var mineDesignData = ArrayList<String>();
        fun getMineDesignInfo() {
            // Read from the database
            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    mineDesignData = ArrayList<String>()
                    for (ds in dataSnapshot.children) {
                        val url = ds.child("imageUrl").getValue(String::class.java)
                        mineDesignData.add(url.toString())
                    }

                    MineDesign.MineDesignStart.loadData(mineDesignData)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Error", "Failed to read value.", error.toException())
                }
            })
        }
    }
}