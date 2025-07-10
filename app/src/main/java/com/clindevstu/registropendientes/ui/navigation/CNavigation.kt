package com.clindevstu.registropendientes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clindevstu.registropendientes.ui.modules.panelcentral.ScreenPanelCentral
import com.clindevstu.registropendientes.ui.modules.splashprincipal.ScreenSplashPrincipal

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.SplashScreen.route) {
        composable(NavRoute.SplashScreen.route) {
            ScreenSplashPrincipal(navController)
        }
        composable(NavRoute.PanelCentral.route) {
            ScreenPanelCentral(navController)
        }
        composable(NavRoute.RegistroInternet.route) {
            RegistroInternetScreen(navController)
        }
        composable(NavRoute.RegistroAverias.route) {
            RegistroAveriasScreen(navController)
        }
        composable(NavRoute.RegistroRecojo.route) {
            RegistroRecojoScreen(navController)
        }
        composable(NavRoute.RegistroCamaras.route) {
            RegistroCamarasScreen(navController)
        }
    }
}
