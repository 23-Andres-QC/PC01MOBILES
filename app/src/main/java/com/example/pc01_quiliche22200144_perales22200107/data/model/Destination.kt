package com.example.pc01_quiliche22200144_perales22200107.data.model

data class Destination(
    val pais: String,
    val ciudad: String,
    val costoPromedio: Double,
    val imageUrl: String
)

val destinationList = listOf(
    Destination(
        pais = "Perú",
        ciudad = "Cusco",
        costoPromedio = 250.0,
        imageUrl = "https://images.unsplash.com/photo-1526392060635-9d6019884377?w=400"
    ),
    Destination(
        pais = "Francia",
        ciudad = "París",
        costoPromedio = 350.0,
        imageUrl = "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?w=400"
    ),
    Destination(
        pais = "Japón",
        ciudad = "Tokio",
        costoPromedio = 400.0,
        imageUrl = "https://images.unsplash.com/photo-1540959733332-eab4deabeeaf?w=400"
    ),
    Destination(
        pais = "Italia",
        ciudad = "Roma",
        costoPromedio = 300.0,
        imageUrl = "https://images.unsplash.com/photo-1552832230-c0197dd311b5?w=400"
    ),
    Destination(
        pais = "México",
        ciudad = "Cancún",
        costoPromedio = 200.0,
        imageUrl = "https://images.unsplash.com/photo-1510097467424-192d713fd8b2?w=400"
    ),
    Destination(
        pais = "Argentina",
        ciudad = "Buenos Aires",
        costoPromedio = 180.0,
        imageUrl = "https://images.unsplash.com/photo-1589909202802-8f4aadce1849?w=400"
    )
)
