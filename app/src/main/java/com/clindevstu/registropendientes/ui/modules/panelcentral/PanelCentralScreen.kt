package com.clindevstu.registropendientes.ui.modules.panelcentral

import androidx.compose.runtime.Composable
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