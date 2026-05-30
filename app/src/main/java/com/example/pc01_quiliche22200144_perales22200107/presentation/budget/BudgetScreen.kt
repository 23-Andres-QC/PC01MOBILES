package com.example.pc01_quiliche22200144_perales22200107.presentation.budget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class TipoAlojamiento(val nombre: String, val factor: Double)

val tiposAlojamiento = listOf(
    TipoAlojamiento("Económico", 0.8),
    TipoAlojamiento("Estándar", 1.0),
    TipoAlojamiento("Premium", 1.5)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(onBack: () -> Unit) {
    var dias by remember { mutableStateOf("") }
    var presupuestoDiario by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(tiposAlojamiento[1]) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    var diasError by remember { mutableStateOf("") }
    var presupuestoError by remember { mutableStateOf("") }

    var resultado by remember { mutableStateOf<Double?>(null) }
    var mensajeResultado by remember { mutableStateOf("") }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text("Ingresa los datos del viaje", style = MaterialTheme.typography.titleMedium)

            // Campo: Cantidad de días
            OutlinedTextField(
                value = dias,
                onValueChange = {
                    dias = it
                    diasError = ""
                },
                label = { Text("Cantidad de días") },
                isError = diasError.isNotEmpty(),
                supportingText = { if (diasError.isNotEmpty()) Text(diasError) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo: Presupuesto diario
            OutlinedTextField(
                value = presupuestoDiario,
                onValueChange = {
                    presupuestoDiario = it
                    presupuestoError = ""
                },
                label = { Text("Presupuesto diario (S/)") },
                isError = presupuestoError.isNotEmpty(),
                supportingText = { if (presupuestoError.isNotEmpty()) Text(presupuestoError) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            // Dropdown: Tipo de alojamiento
            Text("Tipo de alojamiento", style = MaterialTheme.typography.bodyMedium)
            Box {
                OutlinedTextField(
                    value = "${tipoSeleccionado.nombre} (factor ${tipoSeleccionado.factor})",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Alojamiento") },
                    trailingIcon = {
                        IconButton(onClick = { dropdownExpanded = true }) {
                            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Abrir lista")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false }
                ) {
                    tiposAlojamiento.forEach { tipo ->
                        DropdownMenuItem(
                            text = { Text("${tipo.nombre} (×${tipo.factor})") },
                            onClick = {
                                tipoSeleccionado = tipo
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Botón calcular
            Button(
                onClick = {
                    var valido = true

                    val diasVal = dias.toIntOrNull()
                    if (dias.isBlank()) {
                        diasError = "Campo obligatorio"
                        valido = false
                    } else if (diasVal == null) {
                        diasError = "Ingresa un número entero"
                        valido = false
                    } else if (diasVal <= 0) {
                        diasError = "Debe ser mayor a cero"
                        valido = false
                    }

                    val presupuestoVal = presupuestoDiario.toDoubleOrNull()
                    if (presupuestoDiario.isBlank()) {
                        presupuestoError = "Campo obligatorio"
                        valido = false
                    } else if (presupuestoVal == null) {
                        presupuestoError = "Ingresa un valor numérico"
                        valido = false
                    } else if (presupuestoVal <= 0) {
                        presupuestoError = "Debe ser mayor a cero"
                        valido = false
                    }

                    if (valido) {
                        val total = diasVal!!.toDouble() * presupuestoDiario.toDouble() * tipoSeleccionado.factor
                        resultado = total
                        mensajeResultado = when (tipoSeleccionado.nombre) {
                            "Económico" -> "Viaje económico de $diasVal días. Presupuesto ajustado con factor 0.8."
                            "Estándar"  -> "Viaje estándar de $diasVal días. Presupuesto base sin ajuste."
                            "Premium"   -> "Viaje premium de $diasVal días. Presupuesto incrementado con factor 1.5."
                            else        -> ""
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular presupuesto")
            }

            // Resultado
            resultado?.let { total ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Presupuesto total",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "S/ ${"%.2f".format(total)}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = mensajeResultado,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
