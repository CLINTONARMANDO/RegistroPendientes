package com.clindevstu.registropendientes.ui.modules.panelcentral

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ScreenPanelCentral(navController: NavHostController){
    val viewModel : PanelCentralViewModel = hiltViewModel()
    PanelCentralScreen(navController, viewModel)
}

@Composable
fun PanelCentralScreen(
    navController: NavHostController,
    viewModel: PanelCentralViewModel
){

}

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Notificaciones : BottomNavItem("notificaciones", "Notificaciones", Icons.Default.Home)
    object PanelPrincipal : BottomNavItem("panel_principal", "Panel Principal", Icons.Default.Person)
    object Configuraciones : BottomNavItem("configuraciones", "Configuraciones", Icons.Default.BarChart)
}
