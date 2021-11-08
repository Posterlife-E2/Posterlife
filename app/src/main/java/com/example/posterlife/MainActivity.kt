package com.example.posterlife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.ui.core.setContent
import com.example.posterlife.UI.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}





