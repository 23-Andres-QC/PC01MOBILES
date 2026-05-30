package com.example.pc01_quiliche22200144_perales22200107.presentation.luggage

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

// TODO: Pantalla 1 - Calculadora de Equipaje (3 puntos)
// Componentes: Column, OutlinedTextField, RadioButton o DropdownMenu, Button, Text
// Entradas: peso de la maleta, tipo de vuelo (Nacional 23kg / Internacional 32kg)
// Salidas: si cumple o excede el límite, cantidad de kg excedidos
// Validaciones: campo obligatorio, valor numérico, mayor a cero

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuggageScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculadora de Equipaje") },
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
            Text("Pantalla 1: Calculadora de Equipaje")
        }
    }
}
