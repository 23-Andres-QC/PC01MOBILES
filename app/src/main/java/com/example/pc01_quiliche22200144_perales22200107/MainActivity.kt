package com.example.pc01_quiliche22200144_perales22200107

// Integrantes del equipo:
// - Andres Quiliche   (22200144)
// - Alex Perales      (22200107)

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pc01_quiliche22200144_perales22200107.presentation.navigation.AppNavigation
import com.example.pc01_quiliche22200144_perales22200107.ui.theme.PC01Quiliche22200144Perales22200107Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PC01Quiliche22200144Perales22200107Theme {
                AppNavigation()
            }
        }
    }
}
