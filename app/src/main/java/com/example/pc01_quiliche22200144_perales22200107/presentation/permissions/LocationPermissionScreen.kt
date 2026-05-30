package com.example.pc01_quiliche22200144_perales22200107.presentation.permissions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// TODO: Pantalla 4 - Permiso de Ubicación para Asistencia de Viaje (2 puntos)
// Componentes: Column, Text, Button
// Implementar Activity Result API con rememberLauncherForActivityResult
// Solicitar: ACCESS_FINE_LOCATION o ACCESS_COARSE_LOCATION
// Mostrar estado: Permiso concedido / denegado / pendiente de solicitud

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPermissionScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Permiso de Ubicación") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Pantalla 4: Permiso de Ubicación para Asistencia de Viaje")
        }
    }
}
