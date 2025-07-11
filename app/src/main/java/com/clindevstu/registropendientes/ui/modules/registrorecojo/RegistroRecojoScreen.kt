package com.clindevstu.registropendientes.ui.modules.registrorecojo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ScreenRegistroRecojo (navController: NavHostController){
    val viewModel: RegistroRecojoViewModel = hiltViewModel()
    RegistroRecojoScreen(navController, viewModel)
}

@Composable
fun RegistroRecojoScreen (navController: NavHostController, viewModel: RegistroRecojoViewModel){

    val state by viewModel.state.collectAsState()
}