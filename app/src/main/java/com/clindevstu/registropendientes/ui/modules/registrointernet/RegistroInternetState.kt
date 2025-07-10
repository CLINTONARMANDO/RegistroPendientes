package com.clindevstu.registropendientes.ui.modules.registrointernet

import com.clindevstu.registropendientes.ui.modules.splashprincipal.SplashPrincipalState

sealed class RegistroInternetState {
    object Init : RegistroInternetState()
    object Loading : RegistroInternetState()
    object Success : RegistroInternetState()
    data class Error(val message: String) : RegistroInternetState()
}