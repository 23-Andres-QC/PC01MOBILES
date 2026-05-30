package com.example.pc01_quiliche22200144_perales22200107.presentation.budget

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

// TODO: Pantalla 2 - Planificador de Presupuesto de Viaje (4 puntos)
// Componentes: Column, OutlinedTextField, DropdownMenu, Button, Text
// Entradas: cantidad de días, presupuesto diario, tipo de alojamiento
// Tipos de alojamiento: Económico (0.8), Estándar (1.0), Premium (1.5)
// Fórmula: total = días × presupuesto diario × factor
// Salidas: presupuesto total con 2 decimales, mensaje descriptivo
// Validaciones: campos obligatorios, días > 0, presupuesto > 0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Planificador de Presupuesto") },
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
            Text("Pantalla 2: Planificador de Presupuesto de Viaje")
        }
    }
}
