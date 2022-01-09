package com.example.posterlife.jsonParser

import android.util.Log
import com.example.posterlife.ui.MineDesign
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener


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