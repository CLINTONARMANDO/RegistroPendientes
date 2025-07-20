package com.clindevstu.registropendientes.ui.modules.registrointernet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.ui.components.CTopAppBarBody
import com.clindevstu.registropendientes.ui.components.CTopBarSecondary

@Composable
fun ScreenRegistroInternet (navController: NavHostController){
    val viewModel: RegistroInternetViewModel = hiltViewModel()
    RegistroInternetScreen(navController, viewModel)
}

@Composable
fun RegistroInternetScreen (navController: NavHostController, viewModel: RegistroInternetViewModel){

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val usuario by userPreferences.userData.collectAsState(initial = UsuarioResponse())

    LaunchedEffect(Unit) {
        viewModel.obtenerContexto(context)
    }

    Scaffold (
        topBar = {
            CTopBarSecondary(
                title = "Registro Internet",
                subtitle = "Registro de Clientes Internet",
                navController = navController

            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CTopAppBarBody(
                title = "Mis Instalaciones",
                actions = listOf(
                    Icons.Default.Search to { println("Settings clicked") },
                    Icons.Default.AddCircle to { println("More clicked") }
                )
            )



        }


    }

}