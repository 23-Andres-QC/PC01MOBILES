package com.example.pc01_quiliche22200144_perales22200107.presentation.budget

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pc01_quiliche22200144_perales22200107.data.model.TipoAlojamiento
import com.example.pc01_quiliche22200144_perales22200107.data.model.tiposAlojamiento

class BudgetViewModel : ViewModel() {

    var dias by mutableStateOf("")
        private set
    var presupuestoDiario by mutableStateOf("")
        private set
    var tipoSeleccionado by mutableStateOf(tiposAlojamiento[1])
        private set

    var diasError by mutableStateOf("")
        private set
    var presupuestoError by mutableStateOf("")
        private set

    var resultado by mutableStateOf<Double?>(null)
        private set
    var mensajeResultado by mutableStateOf("")
        private set

    fun onDiasChange(value: String) {
        dias = value
        diasError = ""
    }

    fun onPresupuestoChange(value: String) {
        presupuestoDiario = value
        presupuestoError = ""
    }

    fun onTipoChange(tipo: TipoAlojamiento) {
        tipoSeleccionado = tipo
    }

    fun calcular() {
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
            resultado = diasVal!!.toDouble() * presupuestoDiario.toDouble() * tipoSeleccionado.factor
            mensajeResultado = when (tipoSeleccionado.nombre) {
                "Económico" -> "Viaje económico de $diasVal días. Presupuesto ajustado con factor 0.8."
                "Estándar"  -> "Viaje estándar de $diasVal días. Presupuesto base sin ajuste."
                "Premium"   -> "Viaje premium de $diasVal días. Presupuesto incrementado con factor 1.5."
                else        -> ""
            }
        }
    }
}
