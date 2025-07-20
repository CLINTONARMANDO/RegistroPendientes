package com.clindevstu.registropendientes.ui.modules.panelcentral

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.clindevstu.registropendientes.ui.components.CBottomNavigationBar
import com.clindevstu.registropendientes.ui.components.CTopBar
import com.clindevstu.registropendientes.ui.modules.panelcentral.configuraciones.ScreenConfiguraciones
import com.clindevstu.registropendientes.ui.modules.panelcentral.notificaciones.ScreenNotificaciones
import com.clindevstu.registropendientes.ui.modules.panelcentral.panelprincipal.ScreenPanelPrincipal

@Composable
fun ScreenPanelCentral(navController: NavHostController){
    val viewModel : PanelCentralViewModel = hiltViewModel()
    PanelCentralScreen(navController, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanelCentralScreen(
    navController: NavHostController,
    viewModel: PanelCentralViewModel
) {
    // Estado para la pestaña seleccionada
    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.PanelPrincipal) }

    Scaffold(
        topBar = {
            CTopBar(
                title = selectedItem.label,
                onLeftClick = { /* Acción izquierda */ },
                onRightClick = { /* Acción derecha */ }
            )
        },
        bottomBar = {
            CBottomNavigationBar(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                is BottomNavItem.Notificaciones -> ScreenNotificaciones(navController)
                is BottomNavItem.PanelPrincipal -> ScreenPanelPrincipal(navController)
                is BottomNavItem.Configuraciones -> ScreenConfiguraciones(navController)
            }
        }
    }
}





sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Notificaciones : BottomNavItem("notificaciones", "Notificaciones", Icons.Default.Home)
    object PanelPrincipal : BottomNavItem("panel_principal", "Panel Principal", Icons.Default.Person)
    object Configuraciones : BottomNavItem("configuraciones", "Configuraciones", Icons.Default.BarChart)
}
