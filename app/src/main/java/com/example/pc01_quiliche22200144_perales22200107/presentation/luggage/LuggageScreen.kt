package com.example.pc01_quiliche22200144_perales22200107.presentation.luggage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class FlightType(val nombre: String, val pesoMaximo: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuggageScreen(onBack: () -> Unit) {
    val tiposVuelo = listOf(
        FlightType("Nacional", 23),
        FlightType("Internacional", 32)
    )

    var pesoMaleta by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(tiposVuelo[0]) }
    var errorPeso by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<ResultadoEquipaje?>(null) }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Ingrese los datos del equipaje",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = pesoMaleta,
                onValueChange = {
                    pesoMaleta = it
                    errorPeso = ""
                    resultado = null
                },
                label = { Text("Peso de la maleta (kg)") },
                placeholder = { Text("Ej: 20.5") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = errorPeso.isNotEmpty(),
                supportingText = {
                    if (errorPeso.isNotEmpty()) Text(errorPeso, color = MaterialTheme.colorScheme.error)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Text(
                text = "Tipo de vuelo:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )

            tiposVuelo.forEach { tipo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = tipoSeleccionado == tipo,
                        onClick = {
                            tipoSeleccionado = tipo
                            resultado = null
                        }
                    )
                    Text(
                        text = "${tipo.nombre} (máximo ${tipo.pesoMaximo} kg)",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        pesoMaleta = ""
                        tipoSeleccionado = tiposVuelo[0]
                        errorPeso = ""
                        resultado = null
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Limpiar")
                }

                Button(
                    onClick = {
                        val peso = pesoMaleta.trim()
                    when {
                        peso.isEmpty() -> {
                            errorPeso = "El campo es obligatorio"
                            resultado = null
                        }
                        peso.toDoubleOrNull() == null -> {
                            errorPeso = "Debe ingresar un valor numérico"
                            resultado = null
                        }
                        peso.toDouble() <= 0 -> {
                            errorPeso = "El valor debe ser mayor a cero"
                            resultado = null
                        }
                        else -> {
                            errorPeso = ""
                            val pesoDouble = peso.toDouble()
                            val maximo = tipoSeleccionado.pesoMaximo
                            resultado = if (pesoDouble <= maximo) {
                                ResultadoEquipaje(
                                    cumple = true,
                                    mensaje = "El equipaje cumple con el límite permitido",
                                    detalle = "Peso: ${"%.2f".format(pesoDouble)} kg — Límite ${tipoSeleccionado.nombre}: $maximo kg",
                                    exceso = 0.0
                                )
                            } else {
                                val exceso = pesoDouble - maximo
                                ResultadoEquipaje(
                                    cumple = false,
                                    mensaje = "El equipaje excede el límite permitido",
                                    detalle = "Peso: ${"%.2f".format(pesoDouble)} kg — Límite ${tipoSeleccionado.nombre}: $maximo kg",
                                    exceso = exceso
                                )
                            }
                        }
                    }
                },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Calcular")
                }
            }

            resultado?.let { res ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (res.cumple) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (res.cumple) "✓ ${res.mensaje}" else "✗ ${res.mensaje}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (res.cumple) Color(0xFF2E7D32) else Color(0xFFC62828)
                        )
                        Text(
                            text = res.detalle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (res.cumple) Color(0xFF388E3C) else Color(0xFFD32F2F)
                        )
                        if (!res.cumple) {
                            Text(
                                text = "Exceso: ${"%.2f".format(res.exceso)} kg",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFC62828)
                            )
                        }
                    }
                }
            }
        }
    }
}

data class ResultadoEquipaje(
    val cumple: Boolean,
    val mensaje: String,
    val detalle: String,
    val exceso: Double
)
