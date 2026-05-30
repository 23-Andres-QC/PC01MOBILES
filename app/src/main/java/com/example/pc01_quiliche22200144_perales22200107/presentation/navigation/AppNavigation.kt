package com.example.pc01_quiliche22200144_perales22200107.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc01_quiliche22200144_perales22200107.presentation.budget.BudgetScreen
import com.example.pc01_quiliche22200144_perales22200107.presentation.destinations.DestinationsScreen
import com.example.pc01_quiliche22200144_perales22200107.presentation.home.HomeScreen
import com.example.pc01_quiliche22200144_perales22200107.presentation.luggage.LuggageScreen
import com.example.pc01_quiliche22200144_perales22200107.presentation.permissions.LocationPermissionScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToLuggage = { navController.navigate("luggage") },
                onNavigateToBudget = { navController.navigate("budget") },
                onNavigateToDestinations = { navController.navigate("destinations") },
                onNavigateToPermissions = { navController.navigate("location_permission") }
            )
        }
        composable("luggage") {
            LuggageScreen(onBack = { navController.popBackStack() })
        }
        composable("budget") {
            BudgetScreen(onBack = { navController.popBackStack() })
        }
        composable("destinations") {
            DestinationsScreen(onBack = { navController.popBackStack() })
        }
        composable("location_permission") {
            LocationPermissionScreen(onBack = { navController.popBackStack() })
        }
    }
}
