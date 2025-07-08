package com.clindevstu.registropendientes.ui.modules.splashprincipal

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.clindevstu.registropendientes.ui.navigation.NavRoute
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ScreenSplashPrincipal(navController:NavHostController){
    val viewmodel : SplashPrincipalViewModel = hiltViewModel()
    SplashPrincipalScreen(navController, viewmodel)
}

@Composable
fun SplashPrincipalScreen(
    navController: NavHostController,
    viewModel: SplashPrincipalViewModel
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is SplashPrincipalState.Init,
        is SplashPrincipalState.Loading -> {
            LoadingView()
        }

        is SplashPrincipalState.Success -> {
            // Navegamos cuando sea success
            LaunchedEffect(Unit) {
                navController.navigate(NavRoute.PanelCentral.route) {
                    popUpTo(NavRoute.SplashScreen.route) { inclusive = true }
                }
            }
        }

        is SplashPrincipalState.Error -> {
            val error = (state as SplashPrincipalState.Error).message
            ErrorView(message = error)
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ErrorView(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Error: $message", color = MaterialTheme.colorScheme.error)
    }
}
