package com.clindevstu.registropendientes.ui.modules.splashprincipal

sealed class SplashPrincipalState {
    object Init : SplashPrincipalState()
    object Loading : SplashPrincipalState()
    object Success : SplashPrincipalState()
    data class Error(val message: String) : SplashPrincipalState()
}