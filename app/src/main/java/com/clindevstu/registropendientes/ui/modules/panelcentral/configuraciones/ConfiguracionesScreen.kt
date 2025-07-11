package com.clindevstu.registropendientes.ui.modules.panelcentral.configuraciones

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ScreenConfiguraciones(navController: NavHostController){
    val viewModel : ConfiguracionesViewModel = hiltViewModel()
    ConfiguracionesScreen(navController,viewModel)
}

@Composable
fun ConfiguracionesScreen(
    navController: NavHostController,
    viewModel: ConfiguracionesViewModel
){

}