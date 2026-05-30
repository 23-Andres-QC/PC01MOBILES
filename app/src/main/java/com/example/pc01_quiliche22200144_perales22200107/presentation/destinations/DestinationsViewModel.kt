package com.example.pc01_quiliche22200144_perales22200107.presentation.destinations

import androidx.lifecycle.ViewModel
import com.example.pc01_quiliche22200144_perales22200107.data.model.Destination
import com.example.pc01_quiliche22200144_perales22200107.data.model.destinationList

class DestinationsViewModel : ViewModel() {

    val destinations: List<Destination> = destinationList

    val totalDestinos: Int get() = destinations.size

    val sumaTotal: Double get() = destinations.sumOf { it.costoPromedio }
}
