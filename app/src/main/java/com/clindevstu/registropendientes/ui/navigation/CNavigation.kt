package com.clindevstu.registropendientes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clindevstu.registropendientes.ui.modules.login.ScreenLogin
import com.clindevstu.registropendientes.ui.modules.panelcentral.ScreenPanelCentral
import com.clindevstu.registropendientes.ui.modules.panelcentral.configuraciones.ScreenConfiguraciones
import com.clindevstu.registropendientes.ui.modules.panelcentral.notificaciones.ScreenNotificaciones
import com.clindevstu.registropendientes.ui.modules.panelcentral.panelprincipal.ScreenPanelPrincipal
import com.clindevstu.registropendientes.ui.modules.registroaverias.ScreenRegistroAverias
import com.clindevstu.registropendientes.ui.modules.registrocamaras.ScreenRegistroCamaras
import com.clindevstu.registropendientes.ui.modules.registrointernet.ScreenRegistroInternet
import com.clindevstu.registropendientes.ui.modules.registrorecojo.ScreenRegistroRecojo
import com.clindevstu.registropendientes.ui.modules.splashprincipal.ScreenSplashPrincipal

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun CNavigation() {
    val navController = rememberNavController()
    val currentDensity = LocalDensity.current

    CompositionLocalProvider(
        LocalDensity provides Density(currentDensity.density, fontScale = 1f) // fuerza tamaño fijo
    ) {
        NavHost(navController = navController, startDestination = NavRoute.SplashScreen.route) {
            composable(NavRoute.SplashScreen.route) {
                ScreenSplashPrincipal(navController)
            }
            composable(NavRoute.Login.route) {
                ScreenLogin(navController)
            }
            composable(NavRoute.PanelCentral.route) {
                ScreenPanelCentral(navController)
            }
            composable(NavRoute.PanelPrincipal.route) {
                ScreenPanelPrincipal(navController)
            }
            composable(NavRoute.Notificaciones.route) {
                ScreenNotificaciones(navController)
            }
            composable(NavRoute.Configuraciones.route) {
                ScreenConfiguraciones(navController)
            }
            composable(NavRoute.RegistroInternet.route) {
                ScreenRegistroInternet(navController)
            }
            composable(NavRoute.RegistroAverias.route) {
                ScreenRegistroAverias(navController)
            }
            composable(NavRoute.RegistroRecojo.route) {
                ScreenRegistroRecojo(navController)
            }
            composable(NavRoute.RegistroCamaras.route) {
                ScreenRegistroCamaras(navController)
            }
        }
    }
}

