package com.example.pc01_quiliche22200144_perales22200107.presentation.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

enum class EstadoPermiso {
    PENDIENTE,
    CONCEDIDO,
    DENEGADO
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPermissionScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    var estadoPermiso by remember {
        val yaOtorgado = context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
        mutableStateOf(if (yaOtorgado) EstadoPermiso.CONCEDIDO else EstadoPermiso.PENDIENTE)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permisos ->
        val concedido = permisos[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permisos[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        estadoPermiso = if (concedido) EstadoPermiso.CONCEDIDO else EstadoPermiso.DENEGADO
    }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = when (estadoPermiso) {
                    EstadoPermiso.CONCEDIDO -> Color(0xFF2E7D32)
                    EstadoPermiso.DENEGADO  -> Color(0xFFC62828)
                    EstadoPermiso.PENDIENTE -> MaterialTheme.colorScheme.primary
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Permiso de Ubicación para Asistencia de Viaje",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Esta funcionalidad requiere acceso a tu ubicación para brindarte asistencia durante tu viaje.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            EstadoPermisoCard(estadoPermiso)

            Spacer(modifier = Modifier.height(32.dp))

            when (estadoPermiso) {
                EstadoPermiso.PENDIENTE -> {
                    Button(
                        onClick = {
                            permissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text("Solicitar Permiso de Ubicación")
                    }
                }

                EstadoPermiso.CONCEDIDO -> {
                    OutlinedButton(
                        onClick = { estadoPermiso = EstadoPermiso.PENDIENTE },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Restablecer estado")
                    }
                }

                EstadoPermiso.DENEGADO -> {
                    Button(
                        onClick = {
                            permissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Intentar nuevamente")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Si el sistema no muestra el diálogo, ve a Ajustes > Aplicaciones > Permisos",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun EstadoPermisoCard(estado: EstadoPermiso) {
    val (color, icono, titulo, descripcion) = when (estado) {
        EstadoPermiso.PENDIENTE -> Cuadrupla(
            Color(0xFFFFF8E1),
            "⏳",
            "Permiso pendiente de solicitud",
            "Aún no has otorgado el permiso de ubicación. Pulsa el botón para solicitarlo."
        )
        EstadoPermiso.CONCEDIDO -> Cuadrupla(
            Color(0xFFE8F5E9),
            "✓",
            "Permiso concedido",
            "El permiso de ubicación ha sido otorgado correctamente. La app puede acceder a tu ubicación."
        )
        EstadoPermiso.DENEGADO -> Cuadrupla(
            Color(0xFFFFEBEE),
            "✗",
            "Permiso denegado",
            "El permiso de ubicación fue denegado. Puedes intentarlo nuevamente o habilitarlo desde Configuración."
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = icono,
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = when (estado) {
                    EstadoPermiso.CONCEDIDO -> Color(0xFF2E7D32)
                    EstadoPermiso.DENEGADO  -> Color(0xFFC62828)
                    EstadoPermiso.PENDIENTE -> Color(0xFFF57F17)
                },
                textAlign = TextAlign.Center
            )
            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = when (estado) {
                    EstadoPermiso.CONCEDIDO -> Color(0xFF388E3C)
                    EstadoPermiso.DENEGADO  -> Color(0xFFD32F2F)
                    EstadoPermiso.PENDIENTE -> Color(0xFFF9A825)
                }
            )
        }
    }
}

private data class Cuadrupla(
    val color: Color,
    val icono: String,
    val titulo: String,
    val descripcion: String
)
