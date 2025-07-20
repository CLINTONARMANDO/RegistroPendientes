package com.clindevstu.registropendientes.ui.modules.panelcentral.panelprincipal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.clindevstu.registropendientes.R
import com.clindevstu.registropendientes.ui.components.CButtonIconMenuGrid
import com.clindevstu.registropendientes.ui.navigation.NavRoute

@Composable
fun ScreenPanelPrincipal(navController: NavHostController){
    val viewModel: PanelPrincipalVewModel = hiltViewModel()
    PanelPrincipalScreen(navController, viewModel)
}

@Composable
fun PanelPrincipalScreen(
    navController: NavHostController,
    viewModel: PanelPrincipalVewModel
){
    val state by viewModel.state.collectAsState()

    val items = listOf(
        Triple("Registro Internet", painterResource(R.drawable.registro_internet_icon)) {navController.navigate(NavRoute.RegistroInternet.route)},
        Triple("Registro Averias", painterResource(R.drawable.registro_averias_icon)) {navController.navigate(NavRoute.RegistroAverias.route)},
        Triple("Registro Camaras", painterResource(R.drawable.registro_camaras_icon)) {navController.navigate(NavRoute.RegistroCamaras.route)},
        Triple("Registro Recojo", painterResource(R.drawable.registro_recojo_icon)) {navController.navigate(NavRoute.RegistroRecojo.route)},
        Triple("Salir", painterResource(R.drawable.registro_internet_icon)) {}
    )

    Column (modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 24.dp)) {
        CButtonIconMenuGrid(items,3)
    }



}