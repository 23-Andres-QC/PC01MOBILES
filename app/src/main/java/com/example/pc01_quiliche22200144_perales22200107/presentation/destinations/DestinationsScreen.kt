package com.example.pc01_quiliche22200144_perales22200107.presentation.destinations

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

// TODO: Pantalla 3 - Catálogo de Destinos Turísticos (5 puntos)
// Componentes: LazyColumn, Card, Row, Column, AsyncImage (Coil), Text
// Lista simulada con al menos 5 destinos (país, ciudad, costo promedio, URL imagen)
// Cada Card muestra: imagen, país, ciudad, costo promedio
// Al final: cantidad total de destinos y suma acumulada de costos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Destinos") },
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
            Text("Pantalla 3: Catálogo de Destinos Turísticos")
        }
    }
}
