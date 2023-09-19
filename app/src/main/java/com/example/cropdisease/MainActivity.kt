package com.example.cropdisease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cropdisease.pages.NavigationPage
import com.example.cropdisease.ui.theme.CropDiseaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CropDiseaseTheme {
                NavigationPage()

            }
        }
    }
}