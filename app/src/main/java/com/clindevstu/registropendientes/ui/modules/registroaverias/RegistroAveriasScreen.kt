package com.clindevstu.registropendientes.ui.modules.registroaverias

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ScreenRegistroAverias (navController: NavHostController){
    val viewModel: RegistroAveriasViewModel = hiltViewModel()
    RegistroAveriasScreen(navController, viewModel)
}

@Composable
fun RegistroAveriasScreen (navController: NavHostController, viewModel: RegistroAveriasViewModel){

    val state by viewModel.state.collectAsState()

}