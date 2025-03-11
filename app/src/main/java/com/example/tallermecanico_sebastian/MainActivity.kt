package com.example.tallermecanico_sebastian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tallermecanico_sebastian.ui.TallerApp
import com.example.tallermecanico_sebastian.ui.theme.TallerMecanico_SebastianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TallerMecanico_SebastianTheme {
                TallerApp()
            }
        }
    }
}