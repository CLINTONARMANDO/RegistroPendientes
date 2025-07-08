package com.clindevstu.registropendientes.ui.navigation

sealed class NavRoute(val route: String) {
    object SplashScreen : NavRoute("splash_screen")
    object PanelCentral : NavRoute("panel_central")
    object RegistroInternet : NavRoute("registro_internet")
    object RegistroAverias : NavRoute("registro_averias")
    object RegistroRecojo : NavRoute("registro_recojo")
    object RegistroCamaras : NavRoute("registro_camaras")
}
