package com.example.posterlife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.posterlife.view.Navigation

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetupStart()
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun SetupStart() {
    Navigation()
}







