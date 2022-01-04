package com.example.posterlife.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.example.posterlife.R
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView

/**
 * @Source https://github.com/burhanrashid52/PhotoEditor
 */

sealed class BilledRedigering(var rute: String) {

    object BilledConfirm : BilledRedigering("billedConfirm") {

    }

    object BilledRed : BilledRedigering("billedRed") {

        @Composable
        fun BilledRedigering(/*billedSti: String*/) {


            val context = LocalContext.current

            val tekstFont = ResourcesCompat.getFont(context, R.font.roboto)

            val billedRedView = PhotoEditorView(context)
            billedRedView.source.setImageResource(R.drawable.test_image)

            val billedRedTool = PhotoEditor.Builder(context, billedRedView)
                .setPinchTextScalable(true)
                .setClipSourceImage(true)
                .setDefaultTextTypeface(tekstFont)
                .build()

            Column() {


                AndroidView(factory = { billedRedView })
                {
                    billedRedView.apply {

                    }

                }

                Row() {
                    TextButton(
                        onClick = {
                        if (billedRedTool.brushDrawableMode != true) {
                            billedRedTool.setBrushDrawingMode(true)
                        } else billedRedTool.setBrushDrawingMode(false)


                    },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black, contentColor = Color.White
                        )
                    ) {
                        Text("Pensel")
                    }
                }


            }


        }
    }
}