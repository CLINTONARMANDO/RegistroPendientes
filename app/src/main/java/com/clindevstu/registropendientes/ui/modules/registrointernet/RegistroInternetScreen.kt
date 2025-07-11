package com.clindevstu.registropendientes.ui.modules.registrointernet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ScreenRegistroInternet (navController: NavHostController){
    val viewModel: RegistroInternetViewModel = hiltViewModel()
    RegistroInternetScreen(navController, viewModel)
}

@Composable
fun RegistroInternetScreen (navController: NavHostController, viewModel: RegistroInternetViewModel){

    val state by viewModel.state.collectAsState()
}