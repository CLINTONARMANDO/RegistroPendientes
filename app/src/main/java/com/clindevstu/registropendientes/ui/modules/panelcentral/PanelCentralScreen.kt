package com.clindevstu.registropendientes.ui.modules.panelcentral

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.clindevstu.registropendientes.data.preferences.UserPreferences
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
    val isUsuarioDialogActive by viewModel.isUsuarioDialogActive.collectAsState()

    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val usuario by viewModel.usuario.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerContexto(context)
    }

    Scaffold(
        topBar = {
            CTopBar(
                title = selectedItem.label,
                onLeftClick = { /* Acción izquierda */ },
                onRightClick = { viewModel.onUsuarioDialogChange(true) }
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
    if(isUsuarioDialogActive){
        Dialog(onDismissRequest = { viewModel.onUsuarioDialogChange(false) }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Usuario",
                        modifier = Modifier
                            .size(64.dp)
                            .padding(bottom = 16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = usuario.nombre.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = usuario.nombreTecnico.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Soy un trabajador honorable de la empresa.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = { viewModel.onUsuarioDialogChange(false) }) {
                        Text("Cerrar")
                    }
                }
            }
        }
    }
}





sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Notificaciones : BottomNavItem("notificaciones", "Notificaciones", Icons.Default.Home)
    object PanelPrincipal : BottomNavItem("panel_principal", "Panel Principal", Icons.Default.Person)
    object Configuraciones : BottomNavItem("configuraciones", "Configuraciones", Icons.Default.BarChart)
}
