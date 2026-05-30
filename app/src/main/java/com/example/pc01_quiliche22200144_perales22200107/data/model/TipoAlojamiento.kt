package com.example.pc01_quiliche22200144_perales22200107.data.model

data class TipoAlojamiento(val nombre: String, val factor: Double)

val tiposAlojamiento = listOf(
    TipoAlojamiento("Económico", 0.8),
    TipoAlojamiento("Estándar", 1.0),
    TipoAlojamiento("Premium", 1.5)
)
