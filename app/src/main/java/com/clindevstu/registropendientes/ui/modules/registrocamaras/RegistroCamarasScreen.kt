package com.clindevstu.registropendientes.ui.modules.registrocamaras

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ScreenRegistroCamaras (navController: NavHostController){
    val viewModel: RegistroCamarasViewModel = hiltViewModel()
    RegistroCamarasScreen(navController, viewModel)
}

@Composable
fun RegistroCamarasScreen (navController: NavHostController, viewModel: RegistroCamarasViewModel){

    val state by viewModel.state.collectAsState()
}

