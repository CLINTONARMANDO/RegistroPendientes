package com.clindevstu.registropendientes.ui.modules.splashprincipal

sealed class SplashPrincipalState {
    object Init : SplashPrincipalState()
    object Loading : SplashPrincipalState()
    object Success : SplashPrincipalState()
    object GoLogin : SplashPrincipalState()
    data class UpdateRequired(val force: Boolean) : SplashPrincipalState()
    data class Error(val message: String) : SplashPrincipalState()
}
