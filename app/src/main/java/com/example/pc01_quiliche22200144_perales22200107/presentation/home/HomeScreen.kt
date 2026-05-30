package com.example.pc01_quiliche22200144_perales22200107.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToLuggage: () -> Unit,
    onNavigateToBudget: () -> Unit,
    onNavigateToDestinations: () -> Unit,
    onNavigateToPermissions: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Travel Companion App") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Menú Principal",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onNavigateToLuggage,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculadora de Equipaje")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onNavigateToBudget,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Planificador de Presupuesto de Viaje")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onNavigateToDestinations,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Catálogo de Destinos Turísticos")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onNavigateToPermissions,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Permiso de Ubicación para Asistencia de Viaje")
            }
        }
    }
}
