package com.clindevstu.registropendientes.ui.navigation

sealed class NavRoute(val route: String) {
    object SplashScreen : NavRoute("splash_screen")
    object PanelCentral : NavRoute("panel_central")
    object PanelPrincipal : NavRoute("panel_principal")
    object Notificaciones : NavRoute("notificaciones")
    object Configuraciones : NavRoute("configuraciones")
    object RegistroInternet : NavRoute("registro_internet")
    object RegistroAverias : NavRoute("registro_averias")
    object RegistroRecojo : NavRoute("registro_recojo")
    object RegistroCamaras : NavRoute("registro_camaras")
}
